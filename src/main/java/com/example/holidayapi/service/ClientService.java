package com.example.holidayapi.service;

import com.example.holidayapi.entity.Client;
import com.example.holidayapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        // Check if ID already exists
        if (clientRepository.existsById(client.getId())) {
            throw new RuntimeException("Client ID already exists: " + client.getId());
        }
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        // Ensure the client exists
        return clientRepository.findById(id)
                .map(existing -> {
                    existing.setClientName(updatedClient.getClientName());
                    existing.setContactPerson(updatedClient.getContactPerson());
                    existing.setContactEmail(updatedClient.getContactEmail());
                    return clientRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id " + id);
        }
        clientRepository.deleteById(id);
    }
}
