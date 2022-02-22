package com.sf.tool.script.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="TB_TXN_INFO_ERROR")
public class TxnInfoError {
    
    @Id
    private Long txnInfoErrorSeqNo;
    
    private Long batchFileCtrlSeqNo;
    
    private String traceId;
    
    private String spanId;
    
    private String message;
    
    private String errorMsg;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * @return {@link #txnInfoErrorSeqNo}
     */
    public Long getTxnInfoErrorSeqNo() {
        return txnInfoErrorSeqNo;
    }

    /**
     * @param txnInfoErrorSeqNo {@link #txnInfoErrorSeqNo}
     */
    public void setTxnInfoErrorSeqNo(Long txnInfoErrorSeqNo) {
        this.txnInfoErrorSeqNo = txnInfoErrorSeqNo;
    }

    /**
     * @return {@link #traceId}
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId {@link #traceId}
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return {@link #spanId}
     */
    public String getSpanId() {
        return spanId;
    }

    /**
     * @param spanId {@link #spanId}
     */
    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    /**
     * @return {@link #message}
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message {@link #message}
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return {@link #errorMsg}
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg {@link #errorMsg}
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return {@link #createdTime}
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime {@link #createdTime}
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return {@link #batchFileCtrlSeqNo}
     */
    public Long getBatchFileCtrlSeqNo() {
        return batchFileCtrlSeqNo;
    }

    /**
     * @param batchFileCtrlSeqNo {@link #batchFileCtrlSeqNo}
     */
    public void setBatchFileCtrlSeqNo(Long batchFileCtrlSeqNo) {
        this.batchFileCtrlSeqNo = batchFileCtrlSeqNo;
    }
    
}
