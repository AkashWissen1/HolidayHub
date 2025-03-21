package com.example.holidayapi.controller;

import com.example.holidayapi.entity.Holiday;
import com.example.holidayapi.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients/{clientId}/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    // GET /clients/{clientId}/holidays
    @GetMapping
    public List<Holiday> getHolidaysForClient(@PathVariable Long clientId) {
        return holidayService.getHolidaysForClient(clientId);
    }

    // POST /clients/{clientId}/holidays
    @PostMapping
    public ResponseEntity<?> addHolidayToClient(@PathVariable Long clientId,
                                                @RequestBody Holiday holiday) {
        try {
            Holiday created = holidayService.addHolidayToClient(clientId, holiday);
            return ResponseEntity.ok(created);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // PUT /clients/{clientId}/holidays/{holidayId}
    @PutMapping("/{holidayId}")
    public ResponseEntity<?> updateHoliday(@PathVariable Long clientId,
                                           @PathVariable Long holidayId,
                                           @RequestBody Holiday holiday) {
        try {
            Holiday updated = holidayService.updateHoliday(clientId, holidayId, holiday);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // DELETE /clients/{clientId}/holidays/{holidayId}
    @DeleteMapping("/{holidayId}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long clientId,
                                              @PathVariable Long holidayId) {
        try {
            holidayService.deleteHoliday(clientId, holidayId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
