package com.sf.tool.script.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.tool.script.entity.DayEndSignal;
import com.sf.tool.script.repository.DayEndSignalRepository;

@RestController
@RequestMapping(value="/api/dayEndSignal/")
public class DayEndSignalController {
    
    @Autowired
    private DayEndSignalRepository dayEndSignalRepository;
    
    @GetMapping("/list")
    public List<DayEndSignal> list() {
        return IteratorUtils.toList(dayEndSignalRepository.findAll().iterator());
    }
    
    @PutMapping("/{id}")
    public void modify(@PathVariable("id") Long id, @RequestBody DayEndSignal dayEndSignal) {
        dayEndSignalRepository.save(dayEndSignal);
    }
    
    @PutMapping("/")
    public void save(@RequestBody DayEndSignal dayEndSignal) {
        dayEndSignal.setDayEndSignalSeqNo(dayEndSignalRepository.getMaxSeqNo());
        dayEndSignal.setStatementCloseInd("Y");
        dayEndSignal.setUpdatedTime(new Date());
        dayEndSignalRepository.save(dayEndSignal);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        dayEndSignalRepository.deleteById(id);
    }
}
