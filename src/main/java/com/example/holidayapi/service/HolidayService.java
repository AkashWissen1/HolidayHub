package com.example.holidayapi.service;

import com.example.holidayapi.entity.Client;
import com.example.holidayapi.entity.Holiday;
import com.example.holidayapi.repository.ClientRepository;
import com.example.holidayapi.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final ClientRepository clientRepository;

    public HolidayService(HolidayRepository holidayRepository, ClientRepository clientRepository) {
        this.holidayRepository = holidayRepository;
        this.clientRepository = clientRepository;
    }

    public List<Holiday> getHolidaysForClient(Long clientId) {
        return holidayRepository.findByClientId(clientId);
    }

    public Holiday addHolidayToClient(Long clientId, Holiday holiday) {
        // 1) Ensure the client exists
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + clientId));

        // 2) Check for duplicate by date
        if (holidayRepository.existsByClientIdAndHolidayDate(clientId, holiday.getHolidayDate()) && (holidayRepository.existsByClientIdAndHolidayName(clientId, holiday.getHolidayName()))) {
            throw new RuntimeException(
                "A holiday with date " + holiday.getHolidayDate() + " and name "+ holiday.getHolidayName() +" already exists for this client."
            );
        }

        // (Optional) If also checking by holidayName:
        /*
        if (holidayRepository.existsByClientIdAndHolidayName(clientId, holiday.getHolidayName())) {
            throw new RuntimeException(
                "A holiday named '" + holiday.getHolidayName() + "' already exists for this client."
            );
        }
        */

        // 3) Link the holiday to the client
        holiday.setClient(client);

        // 4) Save
        return holidayRepository.save(holiday);
    }

    public Holiday updateHoliday(Long clientId, Long holidayId, Holiday updatedHoliday) {
        // 1) Ensure client exists
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Client not found with id " + clientId);
        }

        // 2) Find existing holiday
        return holidayRepository.findById(holidayId)
                .map(existing -> {
                    // (Optional) If user changes the holiday date, check for duplicates:
                    if (!existing.getHolidayDate().equals(updatedHoliday.getHolidayDate())) {
                        // Check if another holiday with the new date already exists
                        if (holidayRepository.existsByClientIdAndHolidayDate(clientId, updatedHoliday.getHolidayDate())) {
                            throw new RuntimeException("A holiday with date "
                                    + updatedHoliday.getHolidayDate()
                                    + " already exists for this client.");
                        }
                    }

                    // 3) Update fields
                    existing.setHolidayDate(updatedHoliday.getHolidayDate());
                    existing.setHolidayName(updatedHoliday.getHolidayName());

                    // 4) Save changes
                    return holidayRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Holiday not found with id " + holidayId));
    }

    public void deleteHoliday(Long clientId, Long holidayId) {
        if (!clientRepository.existsById(clientId)) {
            throw new RuntimeException("Client not found with id " + clientId);
        }

        Holiday holiday = holidayRepository.findById(holidayId)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id " + holidayId));

        if (!holiday.getClient().getId().equals(clientId)) {
            throw new RuntimeException("Holiday does not belong to client " + clientId);
        }

        holidayRepository.deleteById(holidayId);
    }
}
