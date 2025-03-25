package com.example.authenticationservice.dto;

import lombok.Data;

@Data
public class ForgetPasswordDto {

	private String email;
	private Long id;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
