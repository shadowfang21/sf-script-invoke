package com.sf.tool.script.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sf.tool.script.config.SftpFileClient;

@Controller
@RequestMapping("/api/txnParam")
public class TxnParamController {
    
    @Autowired
    private SftpFileClient sftpFileClient;
    
    @Value("${script-invoke.path}")
    private String scriptInvokePath;
    
    
    
    @GetMapping("/downloadFormat")
    public void downloadFormat(HttpServletRequest requst, HttpServletResponse response) throws IOException {
       
        ClassPathResource templateResource = new ClassPathResource("template.xlsx");
        
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=\"EFAH_param_sample_ESUN_.xlsx\"");
        
        try (InputStream is = templateResource.getInputStream();
             OutputStream os = response.getOutputStream();   
                ) {
            IOUtils.copy(templateResource.getInputStream(), response.getOutputStream());
        }
    }
    
    @GetMapping("/downloadJar")
    public void downloadJar(HttpServletRequest requst, HttpServletResponse response) throws IOException {
       
        File file = new File(scriptInvokePath + "efah_test_program-0.0.1-SNAPSHOT.jar");
        
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=\"efah_test_program-0.0.1-SNAPSHOT.jar\"");
        
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream();   
                ) {
            IOUtils.copy(is, os);
        }
    }
    
    @PostMapping("/upload")
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file) throws Exception {
        
        String fileName = file.getOriginalFilename();
        
        File dir = new File("./upload");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
        
        try (
            InputStream is = file.getInputStream();
        ){
            FileUtils.copyInputStreamToFile(is, serverFile);
        }
        
        return fileName;
    }
    
    @PostMapping("/uploadTxnInfoBatch")
    public @ResponseBody String uploadTxnInfoBatch(@RequestBody Map<String, String> parameter) throws FileNotFoundException, IOException {
        
        File zipFile = zipFile(parameter.get("fileName"));
        
        //upload file to ftp
        sftpFileClient.publish(new FileInputStream(zipFile), "trans-" + zipFile.getName(), parameter.get("uploadPath"));
        
        FileUtils.forceDelete(zipFile);
        
        return "upload done";
    }

    private File zipFile(String fileName) throws IOException, FileNotFoundException {
        File file = new File (scriptInvokePath + "out/" + fileName);
        
        File zipFile = new File(scriptInvokePath + "out/" + FilenameUtils.getBaseName(fileName) + ".zip");
        
        //zip file
        try (
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            FileInputStream fis = new FileInputStream(file);
        ) {
            ZipEntry zipEntry = new ZipEntry("trans-" + FilenameUtils.getBaseName(fileName) + ".txt");
            zipOut.putNextEntry(zipEntry);
            
            org.apache.commons.io.IOUtils.copy(fis, zipOut);
        }
        
        return zipFile;
    }
    
    
}
