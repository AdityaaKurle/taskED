
package com.example.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;
import com.example.employee.dto.EmployeeWithDepartmentDTO;
import com.example.employee.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Controller",
     description = "APIs for managing employees")

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Add employee
    @PostMapping
    @Operation(summary = "Add Employee")
    public ResponseEntity<EmployeeResponseDTO> addEmployee(
            @Valid @RequestBody EmployeeRequestDTO request) {

        EmployeeResponseDTO savedEmployee =
                employeeService.addEmployee(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEmployee);
    }

    // 2. Get all employees
    @GetMapping
    @Operation(summary = "Get All Employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees());
    }

    // 3. Get employee by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get Employee By ID")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable Long id) {

        EmployeeResponseDTO employee =
                employeeService.getEmployeeById(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    // 4. Update employee
    @PutMapping("/{id}")
    @Operation(summary = "Update Employee")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO request) {

        EmployeeResponseDTO updatedEmployee =
                employeeService.updateEmployee(id, request);

        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEmployee);
    }

    // 5. Delete employee
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Employee")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        boolean deleted = employeeService.deleteEmployee(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // 6. Get employee with department details
    @GetMapping("/{id}/details")
    @Operation(summary = "Get Employee With Department Details")
    public ResponseEntity<EmployeeWithDepartmentDTO>
            getEmployeeWithDepartment(@PathVariable Long id) {

        EmployeeWithDepartmentDTO response =
                employeeService.getEmployeeWithDepartment(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}





