package com.example.hrservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.hrservice.dto.ClientAssignDto;
import com.example.hrservice.entities.Client;
import com.example.hrservice.entities.Employee;
import com.example.hrservice.entities.Holiday;
import com.example.hrservice.repositories.ClientRepository;
import com.example.hrservice.repositories.EmployeeRepository;
import com.example.hrservice.repositories.HolidayRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HRService {
	
	@Autowired
	EmployeeRepository employee;
	
	@Autowired
	ClientRepository client;
	
	@Autowired
	HolidayRepository holiday;
	
	@Autowired
    private RestTemplate restTemplate;
	
	private final String EMPLOYEE_SERVICE_URL = "http://EMPLOYEE-SERVICE/employees";
	
	public List<Employee> getEmployee()
	{
		return employee.findAll();
	}
	
	public List<Holiday> getHolidaysForEmployee(Long employeeId) 
	{
	    if (employeeId == null || employeeId <= 0) 
	    {
	        throw new IllegalArgumentException("Invalid employee ID provided.");
	    }

	    Optional<Employee> employeeOpt = employee.findById(employeeId);
	    
	    if (employeeOpt.isEmpty()) 
	    {
	        throw new EntityNotFoundException("Employee with ID " + employeeId + " not found.");
	    }

	    Long clientId = employeeOpt.get().getClientId();
	    if (clientId == null || clientId <= 0) 
	    {
	        throw new EntityNotFoundException("No client associated with the employee.");
	    }

	    List<Holiday> holidays = holiday.findByClientId(clientId);
	    return holidays != null ? holidays : List.of(); // Return an empty list if holidays are null
	}

	public List<Holiday> getHolidaysOfClient(String clientName) 
	{
	    if (clientName == null || clientName.trim().isEmpty()) 
	    {
	        throw new IllegalArgumentException("Client name cannot be null or empty.");
	    }

	    Optional<Client> clientOpt = client.findByClientName(clientName);
	    if (clientOpt.isEmpty()) 
	    {
	        throw new EntityNotFoundException("Client with name '" + clientName + "' not found.");
	    }

	    Long clientId = clientOpt.get().getId();
	    if (clientId == null || clientId <= 0) 
	    {
	        throw new EntityNotFoundException("Invalid client data retrieved.");
	    }

	    List<Holiday> holidays = holiday.findByClientId(clientId);
	    return holidays != null ? holidays : List.of(); // Return an empty list if holidays are null
	}
	
	// Assign Client to Employee
	public boolean assignClientToEmployee(ClientAssignDto dto) 
	{
		Optional<Employee> employeeOpt = employee.findById(dto.getEmployeeId());
	    if (employeeOpt.isEmpty()) 
	    {
	        throw new EntityNotFoundException("Employee with ID " + dto.getEmployeeId() + " not found.");
	    }

	    Employee employee = employeeOpt.get();
	    Optional<Client> clientOpt = client.findById(dto.getClientId());
	    if (clientOpt.isEmpty()) 
	    {
	        throw new EntityNotFoundException("Client with ID " + dto.getClientId() + " not found.");
	    }
	    try
	    {
	    	employee.setClientId(dto.getClientId());
		   // String updateUrl = EMPLOYEE_SERVICE_URL + "/" + employee.getId();
		   // restTemplate.put(updateUrl, employee);
		    return true;
	    }
	    catch(Exception e)
	    {
	    	return false;
	    }  
	}

}
