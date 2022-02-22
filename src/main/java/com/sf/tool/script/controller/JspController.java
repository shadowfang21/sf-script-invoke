package com.sf.tool.script.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspController {
    
    @Value("${server.port:8080}")
    public String serverPort;
    
    @GetMapping("/sales/showPort")
    public ModelAndView sales() {
        Map<String, Object> model = new HashMap<>();
        model.put("serverPort", serverPort);
        
        return new ModelAndView("sales", model);
    }
    
    @GetMapping("/hello/hello")
    public String hello() {
        return "hello";
    }
    
}
