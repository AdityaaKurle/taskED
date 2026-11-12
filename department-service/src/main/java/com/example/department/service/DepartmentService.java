
package com.example.department.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.department.dto.DepartmentRequestDTO;
import com.example.department.dto.DepartmentResponseDTO;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Convert Request DTO to Department Entity
    private Department convertToEntity(DepartmentRequestDTO request) {

        Department department = new Department();

        department.setDepartmentName(request.getDepartmentName());
        department.setDescription(request.getDescription());

        return department;
    }

    // Add a new department
    public DepartmentResponseDTO addDepartment(
            DepartmentRequestDTO request) {

        Department department = convertToEntity(request);

        Department savedDepartment =
                departmentRepository.save(department);

        return new DepartmentResponseDTO(savedDepartment);
    }

    // Get all departments
    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(DepartmentResponseDTO::new)
                .toList();
    }

    // Get department by ID
    public DepartmentResponseDTO getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .map(DepartmentResponseDTO::new)
                .orElse(null);
    }

    // Update an existing department
    public DepartmentResponseDTO updateDepartment(
            Long id, DepartmentRequestDTO request) {

        Department existingDepartment =
                departmentRepository.findById(id).orElse(null);

        if (existingDepartment == null) {
            return null;
        }

        existingDepartment.setDepartmentName(
                request.getDepartmentName());

        existingDepartment.setDescription(
                request.getDescription());

        Department updatedDepartment =
                departmentRepository.save(existingDepartment);

        return new DepartmentResponseDTO(updatedDepartment);
    }

    // Delete a department
    public boolean deleteDepartment(Long id) {

        if (!departmentRepository.existsById(id)) {
            return false;
        }

        departmentRepository.deleteById(id);
        return true;
    }
}


