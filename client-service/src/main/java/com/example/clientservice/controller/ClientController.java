package com.example.clientservice.controller;

import com.example.clientservice.dto.*;
import com.example.clientservice.entity.Client;
import com.example.clientservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService svc;

    public ClientController(ClientService svc) {
        this.svc = svc;
    }

    // 1) Create client
    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody ClientCreateDto dto) {
        return ResponseEntity.ok(svc.createClient(dto));
    }

    // 2) Get client by ID
    @PostMapping("/get")
    public ResponseEntity<Client> getClient(@RequestBody ClientIdDto body) {
        Client c = svc.getClientById(body.getClientId());
        return ResponseEntity.ok(c);
    }

    // 3) Get all clients
    @PostMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(svc.getAllClients());
    }

    // 4) Update client
    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody ClientUpdateDto body) {
        return ResponseEntity.ok(svc.updateClient(body));
    }

    // 5) Delete client
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteClient(@RequestBody ClientIdDto body) {
        svc.deleteClient(body.getClientId());
        return ResponseEntity.noContent().build();
    }
}
