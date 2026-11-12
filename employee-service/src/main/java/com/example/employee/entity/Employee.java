
package com.example.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "employees")
@Schema(description = "Employee information")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique employee ID", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    @Size(max = 100)
    @Schema(description = "Employee full name", example = "Aditya Kurle",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String employeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Schema(description = "Employee email address", example = "aditya@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Designation is required")
    @Schema(description = "Employee job designation", example = "Software Engineer",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String designation;

    @NotNull(message = "Salary is required")
    @PositiveOrZero(message = "Salary cannot be negative")
    @Schema(description = "Employee salary", example = "45000",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Double salary;

    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be greater than zero")
    @Schema(description = "ID of the employee's department", example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Long departmentId;

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}


