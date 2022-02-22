package com.sf.tool.script.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="TB_TXN_BASIC_INFO")
public class TxnBasicInfo {
    
    @Id
    private Long txnBasicInfoSeqNo;
    private String acctDate;
    private String fahGuid;
    private String subTxnNo;
    
    private Long batchFileCtrlSeqNo;
    private String processStatus;
    private String dataValidationErrorCode;
    private String entityCode;
    
    /**
     * @return {@link #txnBasicInfoSeqNo}
     */
    public Long getTxnBasicInfoSeqNo() {
        return txnBasicInfoSeqNo;
    }
    /**
     * @param txnBasicInfoSeqNo {@link #txnBasicInfoSeqNo}
     */
    public void setTxnBasicInfoSeqNo(Long txnBasicInfoSeqNo) {
        this.txnBasicInfoSeqNo = txnBasicInfoSeqNo;
    }
    /**
     * @return {@link #acctDate}
     */
    public String getAcctDate() {
        return acctDate;
    }
    /**
     * @param acctDate {@link #acctDate}
     */
    public void setAcctDate(String acctDate) {
        this.acctDate = acctDate;
    }
    /**
     * @return {@link #fahGuid}
     */
    public String getFahGuid() {
        return fahGuid;
    }
    /**
     * @param fahGuid {@link #fahGuid}
     */
    public void setFahGuid(String fahGuid) {
        this.fahGuid = fahGuid;
    }
    /**
     * @return {@link #subTxnNo}
     */
    public String getSubTxnNo() {
        return subTxnNo;
    }
    /**
     * @param subTxnNo {@link #subTxnNo}
     */
    public void setSubTxnNo(String subTxnNo) {
        this.subTxnNo = subTxnNo;
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
    /**
     * @return {@link #processStatus}
     */
    public String getProcessStatus() {
        return processStatus;
    }
    /**
     * @param processStatus {@link #processStatus}
     */
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
    /**
     * @return {@link #dataValidationErrorCode}
     */
    public String getDataValidationErrorCode() {
        return dataValidationErrorCode;
    }
    /**
     * @param dataValidationErrorCode {@link #dataValidationErrorCode}
     */
    public void setDataValidationErrorCode(String dataValidationErrorCode) {
        this.dataValidationErrorCode = dataValidationErrorCode;
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
    
}
