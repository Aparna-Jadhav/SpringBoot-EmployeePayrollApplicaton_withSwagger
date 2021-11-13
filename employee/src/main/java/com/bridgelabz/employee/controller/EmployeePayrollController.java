package com.bridgelabz.employee.controller;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.employee.DTO.EmployeePayrollDTO;
import com.bridgelabz.employee.DTO.ResponseDTO;
import com.bridgelabz.employee.model.EmployeePayrollData;
import com.bridgelabz.employee.service.IEmployeePayrollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    @RequestMapping(value = { "", "/", "/get" })
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<EmployeePayrollData> empDataList = null;
        empDataList = employeePayrollService.getEmployeePayrollData();
        ResponseDTO responseDTO = new ResponseDTO("Get Call is Successfull !!!", empDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * 
     * @param empId
     * @return
     */
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call for ID Successfull ", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * 
     * @param employeepayrollDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(
            @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {

//        log.debug("Employee DTO : " + employeePayrollDTO.toString());
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = employeePayrollService.createEmployeePayrollData(employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created Employee payroll Data Successfully ", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * 
     * @param employeePayrollDTO
     * @return
     */
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
            @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {

//        log.debug("Employee DTO:" + employeePayrollDTO.toString());
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = employeePayrollService.updateEmployeePayrollData(empId, employeePayrollDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * 
     * @param empId
     * @return
     */
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        employeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO responseDTO = new ResponseDTO("Deleted Successfully ", "Deleted id : " + empId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
