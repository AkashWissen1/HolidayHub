package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.HolidayCreateDto;
import com.example.demo.dto.HolidayResponseDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    private RestTemplate restTemplate;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Convert Employee Entity to DTO
    private EmployeeResponseDTO convertToDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setDesignation(employee.getDesignation());
        dto.setEmail(employee.getEmail());
        dto.setClientId(employee.getClientId());
        return dto;
    }

    // Convert DTO to Employee Entity
    private Employee convertToEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setEmployeeName(dto.getEmployeeName());
        employee.setDesignation(dto.getDesignation());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword()); // No encoding
        employee.setClientId(dto.getClientId());
        return employee;
    }

    // Create Employee
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Get Employee by ID
    public Optional<EmployeeResponseDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(this::convertToDTO);
    }

    // Get All Employees
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update Employee
    public Optional<EmployeeResponseDTO> updateEmployee(Long id, EmployeeRequestDTO updatedEmployeeDTO) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setEmployeeName(updatedEmployeeDTO.getEmployeeName());
            existingEmployee.setDesignation(updatedEmployeeDTO.getDesignation());
            existingEmployee.setEmail(updatedEmployeeDTO.getEmail());
            existingEmployee.setPassword(updatedEmployeeDTO.getPassword());
            existingEmployee.setClientId(updatedEmployeeDTO.getClientId());
            Employee updated = employeeRepository.save(existingEmployee);
            return convertToDTO(updated);
        });
    }

    // Delete Employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<HolidayResponseDTO> getHolidaysForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + employeeId));
    
        Long clientId = employee.getClientId(); // Get clientId from employee
    
        // Define the URL with the path variable
        String url = "http://localhost:8082/holidays/client/" + clientId;
    
        // Make a GET request
        ResponseEntity<HolidayResponseDTO[]> response = restTemplate.getForEntity(url, HolidayResponseDTO[].class);
    
        // Convert array to list and return
        return Arrays.asList(response.getBody());
    }
}
