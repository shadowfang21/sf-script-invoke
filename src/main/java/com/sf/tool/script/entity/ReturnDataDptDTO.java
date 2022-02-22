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
public class ReturnDataDptDTO {

    /** 錯誤訊息 */
    private String errorMessage;

    /** 組織類型 */
    private String orgType;

    /** 單位狀態 */
    private String deptStatus;

    /** 公司代碼 */
    private String companyCode;

    /** 公司名稱 */
    private String companyName;

    /** 所屬業管單位系統代碼 */
    private String manageDeptSystemCode;
    
    /** 所屬業管單位代碼 */
    private String manageDeptCode;

    /** 所屬業管單位名稱 */
    private String manageDeptName;
    
    /** 上層單位系統代碼 */
    private String upperDeptSystemCode;
    
    /** 上層單位代碼 */
    private String upperDeptCode;
    
    /** 上層單位名稱 */
    private String upperDeptSystemName;
    
    /** 單位科別系統代碼 */
    private String deptSystemCode;
    
    /** 單位代碼 */
    private String deptCode;
    
    /** 單位名稱 */
    private String deptName;
    
    /** 單位全名簡稱 */
    private String deptFullShortName;
    
    /** 科別代碼 */
    private String sectCode;
    
    /** 科別名稱 */
    private String sectName;
    
    /** 單位屬性代碼 */
    private String orgPropertyCode;
    
    /** 單位層級代碼 */
    private String orgUnitCode;
    
    /** 單位層級 */
    private String orgUnitName;

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
     * @return the orgType
     */
    @JsonGetter("OrgType")
	public String getOrgType() {
		return orgType;
	}

    /**
     * @param orgType the orgType to set
     */
    @JsonSetter("OrgType")
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
     * @return the deptStatus
     */
    @JsonGetter("DeptStatus")
	public String getDeptStatus() {
		return deptStatus;
	}

    /**
     * @param deptStatus the deptStatus to set
     */
    @JsonSetter("DeptStatus")
	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}

	/**
     * @return the companyCode
     */
    @JsonGetter("CompanyCode")
	public String getCompanyCode() {
		return companyCode;
	}

    /**
     * @param companyCode the companyCode to set
     */
    @JsonSetter("CompanyCode")
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
     * @return the companyName
     */
    @JsonGetter("CompanyName")
	public String getCompanyName() {
		return companyName;
	}

    /**
     * @param companyName the companyName to set
     */
    @JsonSetter("CompanyName")
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
     * @return the manageDeptSystemCode
     */
    @JsonGetter("ManageDeptSystemCode")
	public String getManageDeptSystemCode() {
		return manageDeptSystemCode;
	}

    /**
     * @param manageDeptSystemCode the manageDeptSystemCode to set
     */
    @JsonSetter("ManageDeptSystemCode")
	public void setManageDeptSystemCode(String manageDeptSystemCode) {
		this.manageDeptSystemCode = manageDeptSystemCode;
	}

	/**
     * @return the manageDeptCode
     */
    @JsonGetter("ManageDeptCode")
	public String getManageDeptCode() {
		return manageDeptCode;
	}

    /**
     * @param manageDeptCode the manageDeptCode to set
     */
    @JsonSetter("ManageDeptCode")
	public void setManageDeptCode(String manageDeptCode) {
		this.manageDeptCode = manageDeptCode;
	}

	/**
     * @return the manageDeptName
     */
    @JsonGetter("ManageDeptName")
	public String getManageDeptName() {
		return manageDeptName;
	}

    /**
     * @param manageDeptName the manageDeptName to set
     */
    @JsonSetter("ManageDeptName")
	public void setManageDeptName(String manageDeptName) {
		this.manageDeptName = manageDeptName;
	}

	/**
     * @return the upperDeptSystemCode
     */
    @JsonGetter("UpperDeptSystemCode")
	public String getUpperDeptSystemCode() {
		return upperDeptSystemCode;
	}

    /**
     * @param upperDeptSystemCode the upperDeptSystemCode to set
     */
    @JsonSetter("UpperDeptSystemCode")
	public void setUpperDeptSystemCode(String upperDeptSystemCode) {
		this.upperDeptSystemCode = upperDeptSystemCode;
	}

	/**
     * @return the upperDeptCode
     */
    @JsonGetter("UpperDeptCode")
	public String getUpperDeptCode() {
		return upperDeptCode;
	}

    /**
     * @param upperDeptCode the upperDeptCode to set
     */
    @JsonSetter("UpperDeptCode")
	public void setUpperDeptCode(String upperDeptCode) {
		this.upperDeptCode = upperDeptCode;
	}

	/**
     * @return the upperDeptSystemName
     */
    @JsonGetter("UpperDeptSystemName")
	public String getUpperDeptSystemName() {
		return upperDeptSystemName;
	}

    /**
     * @param upperDeptSystemName the upperDeptSystemName to set
     */
    @JsonSetter("UpperDeptSystemName")
	public void setUpperDeptSystemName(String upperDeptSystemName) {
		this.upperDeptSystemName = upperDeptSystemName;
	}

	/**
     * @return the deptSystemCode
     */
    @JsonGetter("DeptSystemCode")
	public String getDeptSystemCode() {
		return deptSystemCode;
	}

    /**
     * @param deptSystemCode the deptSystemCode to set
     */
    @JsonSetter("DeptSystemCode")
	public void setDeptSystemCode(String deptSystemCode) {
		this.deptSystemCode = deptSystemCode;
	}

	/**
     * @return the deptCode
     */
    @JsonGetter("DeptCode")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
     * @return the deptName
     */
    @JsonGetter("DeptName")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
     * @return the deptFullShortName
     */
    @JsonGetter("DeptFullShortName")
	public String getDeptFullShortName() {
		return deptFullShortName;
	}

	public void setDeptFullShortName(String deptFullShortName) {
		this.deptFullShortName = deptFullShortName;
	}

	/**
     * @return the sectCode
     */
    @JsonGetter("SectCode")
	public String getSectCode() {
		return sectCode;
	}

	public void setSectCode(String sectCode) {
		this.sectCode = sectCode;
	}

	/**
     * @return the sectName
     */
    @JsonGetter("SectName")
	public String getSectName() {
		return sectName;
	}

	public void setSectName(String sectName) {
		this.sectName = sectName;
	}

	/**
     * @return the orgPropertyCode
     */
    @JsonGetter("OrgPropertyCode")
	public String getOrgPropertyCode() {
		return orgPropertyCode;
	}

	public void setOrgPropertyCode(String orgPropertyCode) {
		this.orgPropertyCode = orgPropertyCode;
	}

	/**
     * @return the orgUnitCode
     */
    @JsonGetter("OrgUnitCode")
	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	/**
     * @return the orgUnitName
     */
    @JsonGetter("OrgUnitName")
	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

    

    
    
}



