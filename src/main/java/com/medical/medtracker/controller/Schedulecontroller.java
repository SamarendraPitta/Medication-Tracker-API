package com.medical.medtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medical.medtracker.model.Schedule;
import com.medical.medtracker.service.Scheduleservice;

@RestController
public class Schedulecontroller {
 
	@Autowired
	Scheduleservice scheduleserv;
	
	@GetMapping("/api/v1/intakes/schedule/{scheduleid}")
	public ResponseEntity<Schedule> getScheduledetails(@PathVariable Long scheduleid) {
		Schedule schedule = scheduleserv.getscheduledetails(scheduleid);
		return ResponseEntity.ok(schedule);
	}
}
