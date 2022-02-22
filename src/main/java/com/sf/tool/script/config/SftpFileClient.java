package com.sf.tool.script.config;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.sf.tool.script.entity.FileInfo;

@Component
public class SftpFileClient implements Closeable {
    
    private static final Logger logger = LoggerFactory.getLogger(SftpFileClient.class);
    
    @Value("${remote.sftp.host}")
    private String host;
    
    @Value("${remote.sftp.port}")
    private Integer port;
    
    @Value("${remote.sftp.username}")
    private String username;
    
    @Value("${remote.sftp.password}")
    private String password;
    
    @Value("${remote.sftp.output.path}")
    private String outputDir;
    
    @Value("${remote.sftp.knowHosts}")
    private String knowHosts;
    
    @Value("${remote.sftp.privateKeyPath}")
    private String privateKeyPath;
    
    private Session session = null;
    
    public void publish(InputStream inputStream, String fileName, String uploadPath) throws IOException {
        Channel channel = null;
        Session session = null;
        try (
            InputStream fis = inputStream;
        ) {
            
            JSch jsch = new JSch();
            
            session = connect(jsch);
            
            channel = session.openChannel("sftp"); 
            channel.connect(); 
            ChannelSftp sftp = (ChannelSftp) channel;
            
            logger.info("上傳檔案至{}{}", uploadPath, fileName);
            
            sftp.put(fis, uploadPath + fileName);
            
            logger.info("上傳檔案完成");
            
            sftp.disconnect();
            
        } catch (JSchException | SftpException e) {
            throw new IOException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
    
    public List<FileInfo> listFiles(String downloadPath) throws IOException {
        Channel channel = null;
        Session session = null;
        try {
            
            JSch jsch = new JSch();
            
            session = connect(jsch);
            
            channel = session.openChannel("sftp");
            channel.connect(); 
            ChannelSftp sftp = (ChannelSftp) channel;
            
            logger.info("取得檔案清單:{}", downloadPath);
            Vector<ChannelSftp.LsEntry> files = sftp.ls(downloadPath);
            
            List<FileInfo> fileList = new LinkedList<>();
            for (ChannelSftp.LsEntry entry : files) {
                final String fileName = entry.getFilename();
                
                if (fileName.matches("\\.*")) {
                    continue;
                }
                
                FileInfo f = new FileInfo();
                f.setFileName(fileName);
                f.setDownloadPath(downloadPath);
                f.setFileSize(entry.getAttrs().getSize());
                f.setCreateTime(entry.getAttrs().getMtimeString());
                
                fileList.add(f);
            }
            
            return fileList;
        } catch (JSchException | SftpException e) {
            throw new IOException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
    
    public void downloadStream(String downloadPath, String fileName, OutputStream os) throws IOException {
        Channel channel = null;
        Session session = null;
        try {
            
            JSch jsch = new JSch();
            
            session = connect(jsch);
            
            channel = session.openChannel("sftp");
            channel.connect(); 
            ChannelSftp sftp = (ChannelSftp) channel;
            
            logger.info("下載檔案:{}", downloadPath + fileName);
            
            sftp.get(downloadPath + fileName, os);
        } catch (JSchException | SftpException e) {
            throw new IOException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
    
    public void delete(String downloadPath, String pattern, List<String> fileNames) throws IOException {
        Channel channel = null;
        Session session = null;
        try {
            
            JSch jsch = new JSch();
            
            session = connect(jsch);
            
            channel = session.openChannel("sftp");
            channel.connect(); 
            ChannelSftp sftp = (ChannelSftp) channel;
            
            sftp.cd(downloadPath);
            
            if (fileNames.size() > 0) {
                for (String fileName : fileNames) {
                    logger.info("delete file : {}", fileName);
                    sftp.rm(fileName);
                }
            }
            if (StringUtils.isNotEmpty(pattern)) {
                logger.info("delete file pattern: {}", pattern);
                sftp.rm(pattern);
            }
            
            
        } catch (JSchException | SftpException e) {
            throw new IOException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
    
    public File[] fetchFiles(String downloadPath, String fileNamePrefix, String fileNamePattern) throws IOException {
        
        final Path fileOutputPath = Paths.get(outputDir, fileNamePrefix);
        
        logger.info("檔案下載路徑:{}", fileOutputPath.toString());
        
        fileOutputPath.toFile().mkdirs();
        
        Channel channel = null;
        Session session = null;
        try {
            
            JSch jsch = new JSch();
            
            session = connect(jsch);
            
            channel = session.openChannel("sftp");
            channel.connect(); 
            ChannelSftp sftp = (ChannelSftp) channel;
            
            logger.info("取得檔案清單:{}", downloadPath);
            Vector<ChannelSftp.LsEntry> files = sftp.ls(downloadPath);
            
            List<File> fileList = new LinkedList<>();
            for (ChannelSftp.LsEntry entry : files) {
                final String fileName = entry.getFilename();
                
                if (fileName.startsWith(fileNamePrefix) && fileName.matches(fileNamePattern)) {
                    logger.info("下載檔案:{}", downloadPath + fileName);
                    
                    File downloadFile = downloadFile(sftp, downloadPath, entry, fileNamePrefix);
                    
                    logger.info("下載檔案:{}完成", downloadFile.getAbsolutePath());
                    
                    fileList.add(downloadFile);
                }
            }
            
            return fileList.toArray(new File[] {});
        } catch (JSchException | SftpException e) {
            throw new IOException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private Session connect(JSch jsch) throws JSchException {
        
        if (session == null || !session.isConnected()) {
            Session session;
            if (StringUtils.isNotEmpty(knowHosts) && StringUtils.isNotEmpty(privateKeyPath)) {
                jsch.setKnownHosts(knowHosts);
                jsch.addIdentity(privateKeyPath);
            }
            
            if (port != null) {
                session = jsch.getSession(username, host , port);
            } else {
                session = jsch.getSession(username, host);
            }
            
            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setConfig("StrictHostKeyChecking", "no");
            
            if (StringUtils.isNotEmpty(password)) {
                session.setPassword(password);
            }
            
            logger.info("連接遠端檔案庫");
            session.connect();
            
            this.session = session;
        }
        return session;
    }
    
    private File downloadFile(ChannelSftp sftp, String downloadPath, LsEntry entry, String fileNamePrefix) throws FileNotFoundException, IOException, SftpException {
        File file = Paths.get(outputDir, fileNamePrefix, entry.getFilename()).toFile();
        
        try(
            FileOutputStream fos = new FileOutputStream(file);
        ) {
            sftp.get(downloadPath + entry.getFilename(), fos);
        }
        
        return file;
    }

    @Override
    public void close() throws IOException {
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    /**
     * @param host {@link #host}
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @param port {@link #port}
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @param username {@link #username}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password {@link #password}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param outputDir {@link #outputDir}
     */
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    /**
     * @param knowHosts {@link #knowHosts}
     */
    public void setKnowHosts(String knowHosts) {
        this.knowHosts = knowHosts;
    }

    /**
     * @param privateKeyPath {@link #privateKeyPath}
     */
    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

}
