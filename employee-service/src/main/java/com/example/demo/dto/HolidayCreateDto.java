package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayCreateDto {
    private LocalDate holidayDate;  // Not required for Employee Service
    private String holidayName;  // Not required for Employee Service
    private Long clientId;  // Required
}
