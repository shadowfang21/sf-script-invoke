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
 * <p> 呼叫 HRCommon/Employee 的 Request DTO 物件</p>
 *
 * @author 楊景任
 * @date 2019/01/29
 * @remark
 */
public class HrCommonRqDTO {

    /** 員工編號 */
    String employeeNo;
    
    /** 單位代碼 */
    String deptCode;
    
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
     * @return the deptCode
     */
    @JsonGetter("DeptCode")
    public String getDeptCode() {
        return this.deptCode;
    }

    /**
     * @param deptCode the deptCode to set
     */
    @JsonSetter("DeptCode")
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}



