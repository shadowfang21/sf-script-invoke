package com.sf.tool.script.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.tool.script.entity.TxnInfoError;
import com.sf.tool.script.repository.TxnInfoErrorRespository;

@RestController
@RequestMapping("/api/txnInfoError")
public class TxnInfoErrorController {
    
    @Autowired
    private TxnInfoErrorRespository txnInfoErrorRespository;
    
    @GetMapping("list")
    public List<TxnInfoError> list(String createdTime, String message) {
        return txnInfoErrorRespository.findByFahGuidOrCreatedTime(createdTime, message, 
                PageRequest.of(0, 30, new Sort(Direction.DESC, "txn_info_error_seq_no")));
    }
    
}
