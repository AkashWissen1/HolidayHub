package com.example.holidayapi.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    // User-supplied ID (not auto-generated)
    @Id
    @Column(name = "client_id", nullable = false, unique = true)
    private Long id;

    private String clientName;
    private String contactPerson;
    private String contactEmail;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Holiday> holidays = new ArrayList<>();

    public Client() {
    }

    public Client(Long id, String clientName, String contactPerson, String contactEmail) {
        this.id = id;
        this.clientName = clientName;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
    }

    // Getters / Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }
}
