
package com.example.employee.dto;

import com.example.employee.entity.Employee;

public class EmployeeResponseDTO {

    private Long employeeId;
    private String employeeName;
    private String email;
    private String designation;
    private Double salary;
    private Long departmentId;

    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employeeName = employee.getEmployeeName();
        this.email = employee.getEmail();
        this.designation = employee.getDesignation();
        this.salary = employee.getSalary();
        this.departmentId = employee.getDepartmentId();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmail() {
        return email;
    }

    public String getDesignation() {
        return designation;
    }

    public Double getSalary() {
        return salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}



