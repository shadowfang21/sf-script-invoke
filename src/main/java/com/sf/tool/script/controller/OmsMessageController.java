package com.sf.tool.script.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.tool.script.entity.OmsMessage;
import com.sf.tool.script.repository.OmsMessageRepository;

@RestController
@RequestMapping("/api/omsMessage")
@CrossOrigin
public class OmsMessageController {
    
//    @Autowired
    private OmsMessageRepository omsMessageRepository;
    
    @GetMapping("/list")
    public List<OmsMessage> listAll() {
        return omsMessageRepository.findAllByOrderBySeqNoDesc();
    }
    
    @DeleteMapping("/")
    public void delete() {
//        omsMessageRepository.deleteAll();
    }
    
    @RequestMapping("/efah/{msgId}")
    public String create(@PathVariable("msgId") String msgId, @RequestBody(required=false) String message) {
        
        OmsMessage omsMessage = new OmsMessage();
        omsMessage.setSeqNo(Long.valueOf(UUID.randomUUID().getMostSignificantBits()));
        omsMessage.setMessage(message);
        omsMessage.setUpdatedTime(new Date());
        omsMessage.setMsgId(msgId);
        
//        omsMessageRepository.saveAndFlush(omsMessage);
        
        return String.valueOf(omsMessage.getSeqNo());
    }
    
}
