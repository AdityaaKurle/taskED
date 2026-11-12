
package com.example.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.employee.dto.DepartmentDTO;
import com.example.employee.dto.EmployeeRequestDTO;
import com.example.employee.dto.EmployeeResponseDTO;
import com.example.employee.dto.EmployeeWithDepartmentDTO;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestClient departmentRestClient;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            RestClient departmentRestClient) {

        this.employeeRepository = employeeRepository;
        this.departmentRestClient = departmentRestClient;
    }

    // Convert Request DTO to Employee Entity
    private Employee convertToEntity(EmployeeRequestDTO request) {

        Employee employee = new Employee();

        employee.setEmployeeName(request.getEmployeeName());
        employee.setEmail(request.getEmail());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setDepartmentId(request.getDepartmentId());

        return employee;
    }

    // Add employee
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO request) {

        Employee employee = convertToEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return new EmployeeResponseDTO(savedEmployee);
    }

    // Get all employees
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(EmployeeResponseDTO::new)
                .toList();
    }

    // Get employee by ID
    public EmployeeResponseDTO getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .map(EmployeeResponseDTO::new)
                .orElse(null);
    }

    // Update employee
    public EmployeeResponseDTO updateEmployee(
            Long id, EmployeeRequestDTO request) {

        Employee existingEmployee =
                employeeRepository.findById(id).orElse(null);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setEmployeeName(request.getEmployeeName());
        existingEmployee.setEmail(request.getEmail());
        existingEmployee.setDesignation(request.getDesignation());
        existingEmployee.setSalary(request.getSalary());
        existingEmployee.setDepartmentId(request.getDepartmentId());

        Employee updatedEmployee =
                employeeRepository.save(existingEmployee);

        return new EmployeeResponseDTO(updatedEmployee);
    }

    // Delete employee
    public boolean deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);
        return true;
    }

    // Get employee with department details
    public EmployeeWithDepartmentDTO getEmployeeWithDepartment(Long id) {

        Employee employee =
                employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        DepartmentDTO department = departmentRestClient.get()
                .uri("/api/departments/{id}",
                        employee.getDepartmentId())
                .retrieve()
                .body(DepartmentDTO.class);

        return new EmployeeWithDepartmentDTO(employee, department);
    }
}



