package com.example.clientservice.dto;

import lombok.Data;

@Data
public class ClientUpdateDto {
    private Long clientId;
    private String clientName;
    private String contactPerson;
    private String contactEmail;
}
