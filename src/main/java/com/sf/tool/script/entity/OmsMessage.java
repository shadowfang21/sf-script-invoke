package com.sf.tool.script.entity;

import java.util.Date;

import javax.persistence.Id;

//@Entity(name="TB_TEST_OMS_MESSAGE")
//@Table(name="TB_TEST_OMS_MESSAGE", schema="EFAHDB")
public class OmsMessage {
    
    @Id
    private Long seqNo;
    private String message;
    private String msgId;
    private Date updatedTime;
    
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
     * @return {@link #updatedTime}
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }
    /**
     * @param updatedTime {@link #updatedTime}
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    /**
     * @return {@link #seqNo}
     */
    public Long getSeqNo() {
        return seqNo;
    }
    /**
     * @param seqNo {@link #seqNo}
     */
    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }
    /**
     * @return {@link #msgId}
     */
    public String getMsgId() {
        return msgId;
    }
    /**
     * @param msgId {@link #msgId}
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
    
}
