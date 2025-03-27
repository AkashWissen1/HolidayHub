package com.example.hrservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrservice.dto.ClientAssignDto;
import com.example.hrservice.entities.Employee;
import com.example.hrservice.entities.Holiday;
import com.example.hrservice.services.HRService;

@RestController
@RequestMapping("/hr")
public class HRController {
	
	@Autowired
	HRService hrservice;
	
	
	
	@GetMapping("/search/employee/{employeeId}")
	public ResponseEntity<List<Holiday>> getHolidaysForEmployee(@PathVariable Long employeeId)
	{
		return ResponseEntity.ok(hrservice.getHolidaysForEmployee(employeeId));
	}
	
	@GetMapping("/search/client/{clientName}")
	public ResponseEntity<List<Holiday>> getHolidaysOfClient(@PathVariable String clientName)
	{
		return ResponseEntity.ok(hrservice.getHolidaysOfClient(clientName));
	}
	
	@PutMapping("/assign")
	public ResponseEntity<String> assignClientToEmployee(@RequestBody ClientAssignDto dto) 
	{
	    

	    boolean isAssigned = hrservice.assignClientToEmployee(dto);

	    if (isAssigned) 
	    {
	        return ResponseEntity.ok("Client assigned successfully");
	    } else 
	    {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee or Client not found.");
	    }
	}


}
