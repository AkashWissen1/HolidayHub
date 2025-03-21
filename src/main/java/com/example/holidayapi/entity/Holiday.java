package com.example.holidayapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id", nullable = false, unique = true)
    private Long id;

    private LocalDate holidayDate;
    private String holidayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    // Tells Jackson: when serializing a Holiday, do NOT traverse 'holidays' in the Client
    @JsonIgnoreProperties("holidays")
    private Client client;

    public Holiday() {
    }

    public Holiday(LocalDate holidayDate, String holidayName, Client client) {
        this.holidayDate = holidayDate;
        this.holidayName = holidayName;
        this.client = client;
    }

    // Getters / Setters

    public Long getId() {
        return id;
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

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
