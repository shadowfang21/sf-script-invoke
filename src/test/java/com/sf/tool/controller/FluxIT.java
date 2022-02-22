package com.sf.tool.controller;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Base64;
import java.util.function.Function;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxIT {
    
    @Test
    public void base64() throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "字串文字";
        final byte[] textByte = text.getBytes("UTF-8");
        //編碼
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        //解碼
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
    }
    
    @Test
    public void testEmitOnceTwoSubsequenceSubscriber() {
        
        Mono<Integer> header = Mono.fromSupplier(()-> 1)
                .log()
                .cache(); 
        
        Flux<Integer> f1 = Flux.from(header)
                .map(i -> i* 10)
                .log()
                ;
        
        
        Flux<Integer> f2 = Flux.from(header)
                .map(i -> i* 20)
                .log()
                ;
        
        Flux.merge(f1,f2).subscribe();
        
//        Disposable subscribe = header.subscribe(System.out::println);
    }
    
    @Test
    public void mergeConcatSwitch() throws InterruptedException {
        
        Flux<String> f1 = Flux.range(1, 10)
                .map(String::valueOf)
                //.delayElements(Duration.ofMillis(500))
                .log();
        
        Flux<String> f2 = Flux.interval(Duration.ofSeconds(1))
             .map(l-> "A" + l)
            .log()
            .take(10)
            ;
        
        
        f2.zipWith(f1, (String i, String s) -> s + i).subscribe(System.out::println);
        
        
        
//        Flux.merge(f1, f2).subscribe();
        
        
        Thread.sleep(15000);
    }
    
//    @Test
//    public void dynamicArray() throws InterruptedException {
//        
//        List<String> dataList = new LinkedList<>();
//        
//        Flux<String> f1 = Flux.fromIterable(dataList);
//        
//        f1.subscribe(System.out::println);
//        
//        
//        Thread.sleep(1000);
//    }
    
    @Test
    public void testTransfor() {
        
        Function<Flux<Integer>, Flux<String>> fun = f-> f
                .filter(i-> i % 2 ==0)
                .map(i-> "A" + i)
                .log();
        
        Flux.range(1, 10)
            .transform(fun)
            .subscribe(System.out::println);
        
        Flux.range(2, 20)
            .transform(fun)
            .subscribe(System.out::println);
    }
    
    @Test
    public void boardcast() throws InterruptedException {
        Flux<Long> clockTicks = Flux.interval(Duration.ofSeconds(1)).share();

        clockTicks.subscribe(tick -> System.out.println("clock1 " + tick + "s"));

        Thread.sleep(2000);

        clockTicks.subscribe(tick -> System.out.println("\tclock2 " + tick + "s"));
        
        Thread.sleep(10000);
    }
    
}
