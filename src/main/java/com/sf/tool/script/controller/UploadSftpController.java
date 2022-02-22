package com.sf.tool.script.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sf.tool.script.config.SftpFileClient;

@Controller
@RequestMapping("/api/upload")
public class UploadSftpController {
    
    @Value("${remote.sftp.gts.download.path}")
    private String gtsUploadPath;
    
    @Value("${remote.sftp.edls.download.path}")
    private String edlsUploadPath;
    
    @Autowired
    private SftpFileClient sftpFileClient;
    
    @PostMapping("/upload")
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file, 
            String uploadPath, String prefix, boolean autoZip) throws IOException {
        
        if (autoZip && !file.getOriginalFilename().endsWith(".zip")) {
            //zip file
            try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ZipOutputStream zipOut = new ZipOutputStream(bos);
                InputStream fis = file.getInputStream();
            ) {
                ZipEntry zipEntry = new ZipEntry(prefix + file.getOriginalFilename());
                zipOut.putNextEntry(zipEntry);
                
                org.apache.commons.io.IOUtils.copy(fis, zipOut);
                
                zipOut.flush();
                zipOut.closeEntry();
                zipOut.close();
                
                sftpFileClient.publish(new ByteArrayInputStream(bos.toByteArray()), 
                        getFileName(prefix, FilenameUtils.getBaseName(file.getOriginalFilename())) + ".zip", uploadPath);
            }
        } else {
            sftpFileClient.publish(file.getInputStream(), 
                    getFileName(prefix, file.getOriginalFilename()), uploadPath);
        }
        
        return "upload done";
    }

    private String getFileName(String prefix, String originalFilename) {
        if (StringUtils.isNotEmpty(prefix) && !originalFilename.startsWith(prefix)) {
            return prefix + originalFilename;
        }
        return originalFilename;
    }
    
    @GetMapping("/list")
    public @ResponseBody List<String> list() {
        return Arrays.asList(new String[]{edlsUploadPath, gtsUploadPath});
    }
}
