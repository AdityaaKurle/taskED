
package com.example.department.dto;

import com.example.department.entity.Department;

public class DepartmentResponseDTO {

    private Long departmentId;
    private String departmentName;
    private String description;

    public DepartmentResponseDTO() {
    }

    public DepartmentResponseDTO(Department department) {
        this.departmentId = department.getDepartmentId();
        this.departmentName = department.getDepartmentName();
        this.description = department.getDescription();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDescription() {
        return description;
    }
}

