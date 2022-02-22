package com.sf.tool.script.config;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

public class PropertiesPostProcessor implements EnvironmentPostProcessor {

    private StandardPBEStringEncryptor stdStringEncryptor;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        final String key = environment.getProperty("jasypt.encryptor.secret");
        
        Map<String, Object> pdMap = new HashMap<>();
        
        environment.getPropertySources().forEach(new Consumer<PropertySource>() {

            @Override
            public void accept(PropertySource p) {
                if (p instanceof MapPropertySource && p.getName().contains(".properties")) {
                    MapPropertySource mp = (MapPropertySource) p;
                    
                    for (String name : mp.getPropertyNames()) {
                        if (name.contains("password") && StringUtils.isNotBlank(String.valueOf(mp.getProperty(name)))) {
                            pdMap.put(name, stdStringEncryptor(key).decrypt(
                                    String.valueOf(mp.getProperty(name))));
                        }
                    }
                }
            }
        });
        environment.getPropertySources().addFirst(new MapPropertySource("pdMap", pdMap));
    }
    
    private StandardPBEStringEncryptor stdStringEncryptor(String secret) {
        
        if (stdStringEncryptor != null) {
            return stdStringEncryptor;
        }
        
        String encryotKey = new String(DatatypeConverter.parseBase64Binary(secret), StandardCharsets.UTF_8);
        stdStringEncryptor = new StandardPBEStringEncryptor();
        stdStringEncryptor.setPassword(encryotKey);
        
        return stdStringEncryptor;
    }
    
}
