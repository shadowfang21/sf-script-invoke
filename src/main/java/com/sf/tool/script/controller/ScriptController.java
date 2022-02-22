package com.sf.tool.script.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ScriptController {
    
    private static final Logger logger = LoggerFactory.getLogger(ScriptController.class);
    
    @Value("${script.path}")
    private String[] PATHS;
    
    @Value("${script-invoke.path}")
    private String scriptInvokePath;
    
    @GetMapping(value = "/ping", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> ping(String script) throws Throwable {
        
        String[] command = script.replace("$scriptPath", scriptInvokePath).split(",");
        
        Mono<Process> processMono = Mono.fromSupplier(() -> {
            
            ProcessBuilder processBuilder = new ProcessBuilder();
            
            processBuilder.command(command);
            
            logger.info("execute command : {}", String.join(" ", command));
            
            try {
                return processBuilder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
        })
        .cache();
        
        Flux<String> infoFlux = processMono.flatMapMany((Process process) -> 
                Flux.using(()->new BufferedReader(new InputStreamReader(process.getInputStream())).lines(), 
                        Flux::fromStream, 
                        BaseStream::close)
            ).doOnEach(System.out::println);
        
        Flux<String> errorFlux = processMono.flatMapMany((Process process) -> 
                Flux.using(()->new BufferedReader(new InputStreamReader(process.getErrorStream())).lines(), 
                    Flux::fromStream, 
                    BaseStream::close)
            ).doOnEach(System.out::println);
        
        Flux<String> allInfoFlux = Flux.concat(infoFlux, errorFlux);
               
                
        return allInfoFlux;
    }
    
    @GetMapping("/api/script/list")
    public List<String> list() {
        
        List<String> str = new LinkedList<>();
        
        for (String path : PATHS) {
            Stream.of(new File(path))
                .filter(file -> file.exists() && file.isDirectory())
                .flatMap(file -> Arrays.stream(file.listFiles((File dir, String fileName) -> {
                    return fileName.endsWith(".sh") && !fileName.matches("(start.sh|stop.sh)");
                })))
                .map(f -> path + f.getName())
                .forEach(str::add);
        }
        
        Collections.sort(str);
        
        return str;
    }
    
}
