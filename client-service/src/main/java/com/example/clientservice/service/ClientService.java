package com.example.clientservice.service;

import com.example.clientservice.dto.ClientCreateDto;
import com.example.clientservice.dto.ClientUpdateDto;
import com.example.clientservice.entity.Client;
import com.example.clientservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    public Client createClient(ClientCreateDto dto) {
        Client client = new Client();
        client.setClientName(dto.getClientName());
        client.setContactPerson(dto.getContactPerson());
        client.setContactEmail(dto.getContactEmail());
        return repo.save(client);
    }

    public Client getClientById(Long clientId) {
        return repo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientId));
    }

    public List<Client> getAllClients() {
        return repo.findAll();
    }

    public Client updateClient(ClientUpdateDto dto) {
        Client existing = getClientById(dto.getClientId());
        existing.setClientName(dto.getClientName());
        existing.setContactPerson(dto.getContactPerson());
        existing.setContactEmail(dto.getContactEmail());
        return repo.save(existing);
    }

    public void deleteClient(Long clientId) {
        if (!repo.existsById(clientId)) {
            throw new RuntimeException("Client not found: " + clientId);
        }
        repo.deleteById(clientId);
    }
}
