package com.example.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authenticationservice.dto.ChangePasswordDto;
import com.example.authenticationservice.dto.ForgetPasswordDto;
import com.example.authenticationservice.dto.LoginDto;
import com.example.authenticationservice.entities.Employee;
import com.example.authenticationservice.service.AuthenticationServices;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	AuthenticationServices auth;
	

	
	@PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody LoginDto dto) 
	{
		return ResponseEntity.ok(auth.login(dto));
    }
	
	

	@PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto request) 
	{
        return ResponseEntity.ok(auth.changePassword(request));
    }

   
    @PostMapping("/forgetpassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordDto dto ) 
    {
        return ResponseEntity.ok(auth.forgetPassword(dto));
    } 
}
