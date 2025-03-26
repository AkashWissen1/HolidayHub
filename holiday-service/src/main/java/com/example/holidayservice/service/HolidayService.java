package com.example.holidayservice.service;

import com.example.holidayservice.dto.HolidayCreateDto;
import com.example.holidayservice.dto.HolidayUpdateDto;
import com.example.holidayservice.entity.Holiday;
import com.example.holidayservice.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository repo;

    public HolidayService(HolidayRepository repo) {
        this.repo = repo;
    }

    // 1) Create holiday
    public Holiday createHoliday(HolidayCreateDto dto) {
        Holiday holiday = new Holiday();
        holiday.setHolidayDate(dto.getHolidayDate());
        holiday.setHolidayName(dto.getHolidayName());
        holiday.setClientId(dto.getClientId()); // optional
        return repo.save(holiday);
    }

    // 2) Get holiday by ID
    public Holiday getHolidayById(Long holidayId) {
        return repo.findById(holidayId)
                .orElseThrow(() -> new RuntimeException("Holiday not found: " + holidayId));
    }

    // 3) Get all holidays
    public List<Holiday> getAllHolidays() {
        return repo.findAll();
    }

    // 4) Get all holidays for a client
    public List<Holiday> getHolidaysForClient(Long clientId) {
        return repo.findByClientId(clientId);
    }

    // 5) Update holiday
    public Holiday updateHoliday(HolidayUpdateDto dto) {
        Holiday existing = getHolidayById(dto.getHolidayId());
        if (dto.getHolidayDate() != null) {
            existing.setHolidayDate(dto.getHolidayDate());
        }
        if (dto.getHolidayName() != null) {
            existing.setHolidayName(dto.getHolidayName());
        }
        if (dto.getClientId() != null) {
            existing.setClientId(dto.getClientId());
        }
        return repo.save(existing);
    }

    // 6) Delete holiday
    public void deleteHoliday(Long holidayId) {
        if (!repo.existsById(holidayId)) {
            throw new RuntimeException("Holiday not found: " + holidayId);
        }
        repo.deleteById(holidayId);
    }
}
