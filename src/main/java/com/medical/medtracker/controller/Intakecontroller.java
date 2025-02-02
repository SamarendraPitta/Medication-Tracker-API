package com.medical.medtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medical.medtracker.model.Intake;
import com.medical.medtracker.service.Intakeservice;

@RestController
public class Intakecontroller {

	@Autowired
	Intakeservice intakeserv;
	
	@PostMapping("/api/v1/intakes")
	public Intake recordIntakes(@RequestBody Intake intakereq) {
		return intakeserv.recordintakes(intakereq);
	}
	
}
