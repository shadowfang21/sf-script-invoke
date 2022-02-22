package com.sf.tool.script.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="TB_DAY_END_SIGNAL")
public class DayEndSignal {
    
    @Id
    private Long dayEndSignalSeqNo;
    private String entityCode;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date prevAcctDate;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date newAcctDate;
    
    private String statementCloseInd;
    
    private Date updatedTime;
    
    /**
     * @return {@link #dayEndSignalSeqNo}
     */
    public Long getDayEndSignalSeqNo() {
        return dayEndSignalSeqNo;
    }
    /**
     * @param dayEndSignalSeqNo {@link #dayEndSignalSeqNo}
     */
    public void setDayEndSignalSeqNo(Long dayEndSignalSeqNo) {
        this.dayEndSignalSeqNo = dayEndSignalSeqNo;
    }
    /**
     * @return {@link #entityCode}
     */
    public String getEntityCode() {
        return entityCode;
    }
    /**
     * @param entityCode {@link #entityCode}
     */
    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }
    /**
     * @return {@link #prevAcctDate}
     */
    public Date getPrevAcctDate() {
        return prevAcctDate;
    }
    /**
     * @param prevAcctDate {@link #prevAcctDate}
     */
    public void setPrevAcctDate(Date prevAcctDate) {
        this.prevAcctDate = prevAcctDate;
    }
    /**
     * @return {@link #newAcctDate}
     */
    public Date getNewAcctDate() {
        return newAcctDate;
    }
    /**
     * @param newAcctDate {@link #newAcctDate}
     */
    public void setNewAcctDate(Date newAcctDate) {
        this.newAcctDate = newAcctDate;
    }
    /**
     * @return {@link #statementCloseInd}
     */
    public String getStatementCloseInd() {
        return statementCloseInd;
    }
    /**
     * @param statementCloseInd {@link #statementCloseInd}
     */
    public void setStatementCloseInd(String statementCloseInd) {
        this.statementCloseInd = statementCloseInd;
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
}
