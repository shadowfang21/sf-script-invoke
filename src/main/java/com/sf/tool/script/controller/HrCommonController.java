package com.sf.tool.script.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sf.tool.script.entity.HrCommonDptRsDTO;
import com.sf.tool.script.entity.HrCommonRqDTO;
import com.sf.tool.script.entity.HrCommonRsDTO;
import com.sf.tool.script.entity.ReturnDataDTO;
import com.sf.tool.script.entity.ReturnDataDptDTO;

@RestController
@RequestMapping("api/HRCommon")
public class HrCommonController {
    
    @PostMapping("/Employee")
    public HrCommonRsDTO employee(@RequestBody HrCommonRqDTO request) {
        HrCommonRsDTO rs = new HrCommonRsDTO();
        
        rs.setReturnCode("A0");
        ReturnDataDTO r = new ReturnDataDTO();
        r.setDeptCode("EFAH");
        r.setDeptName("EFAH");
        r.setEmployeeNo(request.getEmployeeNo());
        r.setFullName(request.getEmployeeNo() + "Name");
        r.setStatusCode("1");
        r.setAdAccount("ESB"+request.getEmployeeNo());
        rs.setReturnData(Arrays.asList(r));
        
        return rs;
    }
    
    @PostMapping("/Department")
    public HrCommonDptRsDTO department(@RequestBody HrCommonRqDTO request) {
        HrCommonDptRsDTO rs = new HrCommonDptRsDTO();
        rs.setReturnCode("A0");
        
        ReturnDataDptDTO a = new ReturnDataDptDTO();
        a.setUpperDeptCode(request.getDeptCode() + "UpperDeptCode");
        a.setUpperDeptSystemName(request.getDeptCode() + "UpperDeptSystemName");
        a.setDeptName(request.getDeptCode() + "DeptName");
        rs.setReturnData(Arrays.asList(a));
        
        return rs;
    }
    
}
