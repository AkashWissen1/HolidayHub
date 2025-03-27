package com.example.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.authenticationservice.entities.Employee;

@Repository
public interface AuthenticationRepository extends JpaRepository<Employee, Long>{
	
	Optional<Employee> findByEmail(String email);

}
