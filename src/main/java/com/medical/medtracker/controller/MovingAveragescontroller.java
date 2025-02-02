package com.medical.medtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medical.medtracker.model.MovingAverages;
import com.medical.medtracker.service.Analyticsservice;

@RestController
@RequestMapping("/api/v1/analytics")
public class MovingAveragescontroller {
    
    @Autowired
    private Analyticsservice analyticsserv;
    
    @GetMapping("/moving-averages")
    public MovingAverages getMovingAverages(
        @RequestParam Long userId,
        @RequestParam Long medicationId,
        @RequestParam String type,
        @RequestParam int days
    ) {
        return analyticsserv.calculateMovingAverages(
            userId, medicationId, type, days
        );
    }
}


