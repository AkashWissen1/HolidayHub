package com.example.authenticationservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authenticationservice.dto.ChangePasswordDto;
import com.example.authenticationservice.dto.ForgetPasswordDto;
import com.example.authenticationservice.dto.LoginDto;
import com.example.authenticationservice.entities.Employee;
import com.example.authenticationservice.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	AuthenticationRepository auth;
	
	public List<Employee> getAllEmployee()
	{
		return auth.findAll();
	}
	
	public String login(LoginDto login) 
	{
	    Optional<Employee> employee = auth.findByEmail(login.getEmail());

	    if (employee.isPresent()) 
	    {
	        Employee emp = employee.get();
	        if (emp.getPassword().equals(login.getPassword())) 
	        {
	            return emp.getDesignation(); 
	        } 
	        else 
	        {
	            return "Invalid password!";
	        }
	    } 
	    else 
	    {
	        return "Invalid email!";
	    }
	}
	
	public String changePassword(ChangePasswordDto dto) 
	{
        Optional<Employee> employee = auth.findByEmail(dto.getEmail());
        if (employee.isPresent()) 
        {
            Employee emp = employee.get();
            if (emp.getPassword().equals(dto.getOldPassword())) 
            {
                emp.setPassword(dto.getNewPassword());
                auth.save(emp);
                return "Password changed successfully!";
            } else {
                return "Incorrect old password!";
            }
        }
        return "User not found!";
    }

    
    public String forgetPassword(ForgetPasswordDto dto) 
    {
        Optional<Employee> employee = auth.findByEmail(dto.getEmail());
        
        if (employee.isPresent()) 
        {
        	if( employee.get().getId().equals(dto.getId()))
        	{
        		return employee.get().getPassword();
        	}
        	else
        	{
        		return "Invalid Employee ID!";
        	}
            
        }
        else
        {
        	return "Invalid Email!";
        }
        
    } 


}
