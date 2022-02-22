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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * <p> 呼叫 HRCommon/Employee 的 Response DTO 物件 </p>
 *
 * @author 楊景任
 * @date 2019/01/29
 * @remark
 */
public class HrCommonDptRsDTO {

    /** 執行結果代碼 */
    private String returnCode;

    /** 執行結果訊息 */
    private String returnMessage;

    /** 回傳資料 */
    private List<ReturnDataDptDTO> returnData;
    
    /**
     * @return the returnCode
     */
    @JsonGetter("ReturnCode")
    public String getReturnCode() {
        return this.returnCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    @JsonSetter("ReturnCode")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnMessage
     */
    @JsonGetter("ReturnMessage")
    public String getReturnMessage() {
        return this.returnMessage;
    }

    /**
     * @param returnMessage the returnMessage to set
     */
    @JsonSetter("ReturnMessage")
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    /**
     * @return the returnData
     */
    @JsonGetter("ReturnData")
    public List<ReturnDataDptDTO> getReturnData() {
        return this.returnData;
    }

    /**
     * @param returnData the returnData to set
     */
    @JsonSetter("ReturnData")
    public void setReturnData(List<ReturnDataDptDTO> returnData) {
        this.returnData = returnData;
    }

}



