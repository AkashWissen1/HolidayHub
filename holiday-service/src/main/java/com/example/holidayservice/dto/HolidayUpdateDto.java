package com.example.holidayservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HolidayUpdateDto {
    private Long holidayId;
    private LocalDate holidayDate;
    private String holidayName;
    private Long clientId;  // optional if you want to reassign
}
