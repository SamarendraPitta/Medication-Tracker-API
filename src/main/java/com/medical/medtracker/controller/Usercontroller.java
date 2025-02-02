package com.medical.medtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medical.medtracker.model.Users;
import com.medical.medtracker.service.Userservice;

@Controller
public class Usercontroller {
	
	@Autowired
	Userservice userserv;
	
	@GetMapping("/api/v1/intakes/user/{userid}")
	public ResponseEntity<Users> getuserdetails(@PathVariable long userid){
		Users user = userserv.getuserdetails(userid);
		return ResponseEntity.ok(user);
	}

}
