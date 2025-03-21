package com.example.holidayapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.holidayapi.entity.Holiday;
import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    // Returns true if a holiday with the same date already exists for this client
    boolean existsByClientIdAndHolidayDate(Long clientId, LocalDate holidayDate);
    boolean existsByClientIdAndHolidayName(Long clientId, String holidayName);
    
	List<Holiday> findByClientId(Long clientId);

	

    // (Optional) If you also want to prevent duplicates by holidayName:
    // boolean existsByClientIdAndHolidayName(Long clientId, String holidayName);
}
