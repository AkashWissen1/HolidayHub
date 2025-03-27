package com.example.hrservice.dto;

import lombok.Data;

@Data
public class ClientAssignDto {
	
	private Long employeeId;
    private Long clientId;
    
    
	public ClientAssignDto() {
		super();
		
	}
	
	public ClientAssignDto(Long employeeId, Long clientId) {
		super();
		this.employeeId = employeeId;
		this.clientId = clientId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
    
    

}
