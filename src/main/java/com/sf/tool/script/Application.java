/*
 * ===========================================================================
 * IBM Confidential
 * AIS Source Materials
 * 
 * 
 * (C) Copyright IBM Corp. 2018.
 *
 * ===========================================================================
 */
package com.sf.tool.script;


import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p> API Server(參數查詢管理) - Spring Boot 主要應用程式 </p>
 *
 * @author  Jimmy Chen
 * @version 1.0, 2018年12月18日
 * 
 * @since 
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    
    /** logger 物件 */
    private static final Logger logger = LoggerFactory.getLogger(Application.class); 
    
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC+8"));
    }
    
    /**
     * Main 啟動點
     * @param args
     * @throws Throwable
     * @remark
     */
    public static void main(String[] args) throws Throwable {
    	SpringApplication.run(Application.class, args);
    }
    
}



 