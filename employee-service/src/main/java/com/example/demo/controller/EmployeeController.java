package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.HolidayResponseDTO;
import com.example.demo.service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeResponseDTO> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO updatedEmployeeDTO) {
        Optional<EmployeeResponseDTO> updated = employeeService.updateEmployee(id, updatedEmployeeDTO);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? ResponseEntity.ok("Employee deleted successfully")
                       : ResponseEntity.notFound().build();
    }

    @GetMapping("/{employeeId}/holidays")
    public ResponseEntity<List<HolidayResponseDTO>> getHolidaysForEmployee(@PathVariable Long employeeId) {
        List<HolidayResponseDTO> holidays = employeeService.getHolidaysForEmployee(employeeId);
        return ResponseEntity.ok(holidays);
    }
}