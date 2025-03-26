package com.example.holidayservice.controller;

import com.example.holidayservice.dto.*;
import com.example.holidayservice.entity.Holiday;
import com.example.holidayservice.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService svc;

    public HolidayController(HolidayService svc) {
        this.svc = svc;
    }

    // 1) Create holiday
    @PostMapping("/create")
    public ResponseEntity<Holiday> createHoliday(@RequestBody HolidayCreateDto dto) {
        Holiday created = svc.createHoliday(dto);
        return ResponseEntity.ok(created);
    }

    // 2) Get holiday by ID
    @GetMapping("/{holidayId}")
    public ResponseEntity<Holiday> getHoliday(@PathVariable Long holidayId) {
        Holiday holiday = svc.getHolidayById(holidayId);
        return ResponseEntity.ok(holiday);
    }

    // 3) Get all holidays
    @GetMapping
    public ResponseEntity<List<Holiday>> getAllHolidays() {
        return ResponseEntity.ok(svc.getAllHolidays());
    }

    // 4) Get holidays for a client
    @GetMapping("/client/{clientId}")  
    public ResponseEntity<List<Holiday>> getHolidaysForClient(@PathVariable Long clientId) {
        List<Holiday> list = svc.getHolidaysForClient(clientId);
        return ResponseEntity.ok(list);
    }

    // 5) Update holiday
    @PutMapping("/update")
    public ResponseEntity<Holiday> updateHoliday(@RequestBody HolidayUpdateDto dto) {
        return ResponseEntity.ok(svc.updateHoliday(dto));
    }

    // 6) Delete holiday
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteHoliday(@RequestBody HolidayIdDto body) {
        svc.deleteHoliday(body.getHolidayId());
        return ResponseEntity.noContent().build();
    }
}
