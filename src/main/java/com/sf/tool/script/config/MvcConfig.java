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
package com.sf.tool.script.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p> WebMVC 的設定物件</p>
 *
 * @author  Jimmy Chen
 * @version 1.0, 2018年9月27日
 * 
 * @since 
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    

//    @Bean
//    public ViewResolver viewResolver() {
//        final InternalResourceViewResolver view = new InternalResourceViewResolver();
//        view.setPrefix("/WEB-INF/views/");
//        view.setSuffix(".jsp");
//        return view;
//    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//            .addResourceLocations("/resources/");   
//        registry.addResourceHandler("/webjars/**")
//            .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
    
//    /**
//     * 設定允許跨網域可以存取.
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowCredentials(false).allowedOrigins("*").allowedMethods("POST", "GET");
//    }
    
//    /**
//     * 取得 RestClient 物件
//     * @return result
//     * @remark
//     */
//    @Bean
//    RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        restTemplate.getMessageConverters().add(converter);
//        return restTemplate;
//    }
}



 