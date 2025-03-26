package com.example.demo.dto;

import java.time.LocalDate;

public class HolidayResponseDTO {
    private Long id;
    private LocalDate holidayDate;
    private String holidayName;
    private Long clientId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void set3Id(Long id) {
        this.id = id;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
