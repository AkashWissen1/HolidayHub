package com.example.holidayservice.repository;

import com.example.holidayservice.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    List<Holiday> findByClientId(Long clientId);
}
