package com.bridgelabz.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.employee.DTO.EmployeePayrollDTO;
import com.bridgelabz.employee.exception.EmployeePayrollException;
import com.bridgelabz.employee.model.EmployeePayrollData;
import com.bridgelabz.employee.repository.EmployeePayrollRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    EmployeePayrollRepository employeePayrollRepository;

    List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollRepository.findAll();

    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollRepository.findById(empId).orElseThrow(
                () -> new EmployeePayrollException("Employee with EmployeeID" + empId + "does not Exists.....!!"));
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDTO);
//        log.debug("Employee Data : " + employeePayrollData.toString());
        return employeePayrollRepository.save(employeePayrollData);
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.updateEmployeePayrollData(employeePayrollDTO);
        return employeePayrollRepository.save(employeePayrollData);
    }

    // @Override
    // public EmployeePayrollData updateEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
    //     return null;
    // }

    @Override
    public void deleteEmployeePayrollData(int empId) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollRepository.delete(employeePayrollData);
        
    }

}
