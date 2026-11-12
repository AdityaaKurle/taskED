
package com.example.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Department information received from Department Service")
public class DepartmentDTO {

    @Schema(description = "Unique department ID", example = "1")
    private Long departmentId;

    @Schema(description = "Department name", example = "IT")
    private String departmentName;

    @Schema(description = "Department description",
            example = "Information Technology Department")
    private String description;

    public DepartmentDTO() {
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


