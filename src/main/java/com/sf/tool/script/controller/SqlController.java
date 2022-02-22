package com.sf.tool.script.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.tool.script.repository.NativeRespository;

@RestController
@RequestMapping("/api/sql")
public class SqlController {
    
    @Autowired
    private NativeRespository nativeRespository;
    
    @PostMapping("query")
    public List<Map<String, Object>> query(@RequestBody String sql) {
        return nativeRespository.query(sql);
    }
    
    @PostMapping("execute")
    @Transactional
    public String execute(@RequestBody String sql) {
        
        for (String statement : sql.trim().split(";")) {
            nativeRespository.execute(statement.trim().replaceAll("\r", " ").replaceAll("\n", " "));
        }
        return "success";
    }
    
}
