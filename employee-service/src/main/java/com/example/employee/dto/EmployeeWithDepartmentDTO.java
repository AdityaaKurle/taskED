
package com.example.employee.dto;

import com.example.employee.entity.Employee;

public class EmployeeWithDepartmentDTO {

    private Long employeeId;
    private String employeeName;
    private String email;
    private String designation;
    private Double salary;
    private Long departmentId;
    private DepartmentDTO department;

    public EmployeeWithDepartmentDTO(
            Employee employee, DepartmentDTO department) {

        this.employeeId = employee.getEmployeeId();
        this.employeeName = employee.getEmployeeName();
        this.email = employee.getEmail();
        this.designation = employee.getDesignation();
        this.salary = employee.getSalary();
        this.departmentId = employee.getDepartmentId();
        this.department = department;
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

    public DepartmentDTO getDepartment() {
        return department;
    }
}
