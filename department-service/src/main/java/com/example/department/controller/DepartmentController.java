
package com.example.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.department.dto.DepartmentRequestDTO;
import com.example.department.dto.DepartmentResponseDTO;
import com.example.department.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/departments")
@Tag(name = "Department Controller", description = "APIs for managing departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 1. Add a new department
    @PostMapping
    @Operation(summary = "Add Department", description = "Creates a new department")
    public ResponseEntity<DepartmentResponseDTO> addDepartment(
            @Valid @RequestBody DepartmentRequestDTO departmentRequest) {

        DepartmentResponseDTO savedDepartment =
                departmentService.addDepartment(departmentRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedDepartment);
    }

    // 2. Get all departments
    @GetMapping
    @Operation(summary = "Get All Departments", description = "Retrieves all departments")
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentService.getAllDepartments());
    }

    // 3. Get department by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Department By ID", description = "Retrieves a department using its ID")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(
            @PathVariable Long id) {

        DepartmentResponseDTO department =
                departmentService.getDepartmentById(id);

        if (department == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(department);
    }

    // 4. Update department
    @PutMapping("/{id}")
    @Operation(summary = "Update Department", description = "Updates an existing department using its ID")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO departmentRequest) {

        DepartmentResponseDTO updatedDepartment =
                departmentService.updateDepartment(id, departmentRequest);

        if (updatedDepartment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedDepartment);
    }

    // 5. Delete department
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Department", description = "Deletes a department using its ID")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {

        boolean deleted = departmentService.deleteDepartment(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}



