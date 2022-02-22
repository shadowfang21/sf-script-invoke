package com.sf.tool.script.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.tool.script.config.SftpFileClient;
import com.sf.tool.script.entity.FileInfo;

@Controller
@RequestMapping("/api/download")
public class DownloadSftpController {
    
    @Value("${remote.sftp.all.path}")
    private List<String> allPath;
    
    @Autowired
    private SftpFileClient sftpFileClient;
    
    @GetMapping("/download")
    public void download(String downloadPath, String fileName, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
        
        sftpFileClient.downloadStream(downloadPath, fileName, response.getOutputStream());
    }
    
    @DeleteMapping("/deleteFile")
    public @ResponseBody String delete(@RequestBody DeleteInfo deleteInfo) throws IOException {
        
        sftpFileClient.delete(deleteInfo.downloadPath, deleteInfo.pattern, deleteInfo.fileNames);
        
        return "delete done";
    }
    
    @GetMapping("/listFile")
    public @ResponseBody List<FileInfo> listFile(String downloadPath) throws IOException {
        return sftpFileClient.listFiles(downloadPath);
    }
    
    @GetMapping("/listPath")
    public @ResponseBody List<String> list() {
        return allPath;
    }
    
    public static class DeleteInfo {
        private String downloadPath; 
        private String pattern;
        private List<String> fileNames;
        /**
         * @return {@link #downloadPath}
         */
        public String getDownloadPath() {
            return downloadPath;
        }
        /**
         * @param downloadPath {@link #downloadPath}
         */
        public void setDownloadPath(String downloadPath) {
            this.downloadPath = downloadPath;
        }
        /**
         * @return {@link #pattern}
         */
        public String getPattern() {
            return pattern;
        }
        /**
         * @param pattern {@link #pattern}
         */
        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
        /**
         * @return {@link #fileNames}
         */
        public List<String> getFileNames() {
            return fileNames;
        }
        /**
         * @param fileNames {@link #fileNames}
         */
        public void setFileNames(List<String> fileNames) {
            this.fileNames = fileNames;
        }
    }
    
}
