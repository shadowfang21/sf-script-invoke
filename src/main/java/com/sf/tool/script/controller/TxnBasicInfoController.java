package com.sf.tool.script.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.tool.script.entity.TxnBasicInfo;
import com.sf.tool.script.repository.TxnBasicInfoRespository;

@RestController
@RequestMapping("/api/txnBasicInfo")
public class TxnBasicInfoController {
    
    @Autowired
    private TxnBasicInfoRespository txnBasicInfoRespository;
    
    @Autowired
    private TransactionTemplate transactionTemplate;
    
    @GetMapping("/list")
    public List<TxnBasicInfo> list(String acctDate, String fahGuid, String subTxnNo) {
        
        ExampleMatcher withMatcher = ExampleMatcher.matchingAll()
            .withMatcher("fahGuid", new GenericPropertyMatcher().contains());
        
        TxnBasicInfo exampleModel = new TxnBasicInfo();
        exampleModel.setAcctDate(StringUtils.defaultIfBlank(acctDate, null));
        exampleModel.setFahGuid(StringUtils.defaultIfBlank(fahGuid, null));
        exampleModel.setSubTxnNo(StringUtils.defaultIfBlank(subTxnNo, null));
        
        Example<TxnBasicInfo> example = Example.of(exampleModel, withMatcher);
        
        Sort sort = Sort.by("txnBasicInfoSeqNo");
        
        return txnBasicInfoRespository.findAll(example, sort);
    }
    
    @PutMapping("/changeSubTxnNo")
    public String changeSubTxnNo(@RequestBody TxnBasicInfo exampleModel) {
        
        ExampleMatcher withMatcher = ExampleMatcher.matchingAll()
                .withMatcher("fahGuid", new GenericPropertyMatcher().contains());
            
        Example<TxnBasicInfo> example = Example.of(exampleModel, withMatcher);
        
        Sort sort = Sort.by("txnBasicInfoSeqNo");
            
        List<TxnBasicInfo> findAll = txnBasicInfoRespository.findAll(example, sort);
        
        transactionTemplate.execute(s -> {
            for (TxnBasicInfo b : findAll) {
                txnBasicInfoRespository.updateTxnBasicSubTxnNo(b.getTxnBasicInfoSeqNo());
                txnBasicInfoRespository.updateAcctEntry(b.getTxnBasicInfoSeqNo(), 
                        Date.from(LocalDate.parse(b.getAcctDate(), DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay(ZoneId.systemDefault()).toInstant()), b.getFahGuid(), b.getSubTxnNo());
                txnBasicInfoRespository.updateTxnStatementResult(b.getTxnBasicInfoSeqNo(), 
                        b.getAcctDate(), b.getFahGuid(), b.getSubTxnNo());
            }
            return null;
        });
        
        
        return "update done";
    }
    
}
