package com.sf.tool.script.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sendKafka/")
public class SendKafkaController {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @PostMapping("/publichKafkaMessage")
    public String publichKafkaMessage(@RequestBody Message message) throws InterruptedException, ExecutionException {
        
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(message.topic, message.json);
        
        SendResult<String, String> sendResult = send.get();
        
        return sendResult.toString();
    }
    
    public static class Message {
        String json;
        String topic;
        /**
         * @return {@link #json}
         */
        public String getJson() {
            return json;
        }
        /**
         * @param json {@link #json}
         */
        public void setJson(String json) {
            this.json = json;
        }
        /**
         * @return {@link #topic}
         */
        public String getTopic() {
            return topic;
        }
        /**
         * @param topic {@link #topic}
         */
        public void setTopic(String topic) {
            this.topic = topic;
        }
    }
    
}
