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
package com.sf.tool.script.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * <p> 呼叫 HRCommon 的 ReturnData DTO 物件</p>
 *
 * @author 楊景任
 * @date 2019/01/29
 * @remark
 */
public class ReturnDataDTO {

    /** 錯誤訊息 */
    private String errorMessage;

    /** 員工編號 */
    private String employeeNo;

    /** 中文姓名 */
    private String fullName;

    /** 在職狀況 */
    private String statusCode;

    /** 所屬單位代碼 */
    private String deptCode;

    /** 所屬單位名稱 */
    private String deptName;
    
    /** adfs account */
    private String adAccount;
    
    /**
     * @return the errorMessage
     */
    @JsonGetter("ErrorMessage")
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    @JsonSetter("ErrorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the employeeNo
     */
    @JsonGetter("EmployeeNo")
    public String getEmployeeNo() {
        return this.employeeNo;
    }

    /**
     * @param employeeNo the employeeNo to set
     */
    @JsonSetter("EmployeeNo")
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    /**
     * @return the fullName
     */
    @JsonGetter("FullName")
    public String getFullName() {
        return this.fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    @JsonSetter("FullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the statusCode
     */
    @JsonGetter("EmployedStatusCode")
    public String getStatusCode() {
        return this.statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    @JsonSetter("EmployedStatusCode")
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the deptCode
     */
    @JsonGetter("EmpDeptCode")
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * @param deptCode the deptCode to set
     */
    @JsonSetter("EmpDeptCode")
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * @return the deptName
     */
    @JsonGetter("EmpDeptName")
    public String getDeptName() {
        return this.deptName;
    }

    /**
     * @param deptName the deptName to set
     */
    @JsonSetter("EmpDeptName")
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return {@link #aDAccount}
     */
    @JsonGetter("ADAccount")
    public String getAdAccount() {
        return adAccount;
    }

    /**
     * @param aDAccount {@link #aDAccount}
     */
    @JsonSetter("ADAccount")
    public void setAdAccount(String adAccount) {
        this.adAccount = adAccount;
    }
}



