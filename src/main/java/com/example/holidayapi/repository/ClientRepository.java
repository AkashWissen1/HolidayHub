package com.example.holidayapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.holidayapi.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // <Client, Long> because our @Id is Long
}
