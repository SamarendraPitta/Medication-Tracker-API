package com.medical.medtracker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.medtracker.model.Compliance;
import com.medical.medtracker.service.Complianceservice;

@RestController
@RequestMapping("/api/v1/compliance")
public class Compliancecontroller {
    
    @Autowired
    private Complianceservice complianceService;
    
    @GetMapping("/rate")
    public ResponseEntity<Compliance> getComplianceRate(
            @RequestParam Long userId,
            @RequestParam Long medicationId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        
        Compliance response = complianceService.calculateComplianceRate(
            userId, medicationId, startDate, endDate);
        return ResponseEntity.ok(response);
    }
}

