package com.example.clientservice.dto;

import lombok.Data;

@Data
public class ClientCreateDto {
    private String clientName;
    private String contactPerson;
    private String contactEmail;
}
