package com.sf.tool.script.entity;

public class FileInfo {
    
    private String downloadPath;
    private String fileName;
    private String createTime;
    private Long fileSize;
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
     * @return {@link #fileName}
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @param fileName {@link #fileName}
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return {@link #createTime}
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime {@link #createTime}
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    /**
     * @return {@link #fileSize}
     */
    public Long getFileSize() {
        return fileSize;
    }
    /**
     * @param fileSize {@link #fileSize}
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
}
