package com.example.holidayservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayCreateDto {
    private LocalDate holidayDate;
    private String holidayName;
    private Long clientId;  // optional
}
