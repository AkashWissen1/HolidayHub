package com.example.hrservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hrservice.entities.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long>{

	List<Holiday> findByClientId(Long clientId);
	

}
