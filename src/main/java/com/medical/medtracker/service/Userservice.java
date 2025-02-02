package com.medical.medtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.medtracker.model.Users;
import com.medical.medtracker.repository.UserRepo;

@Service
public class Userservice {

	@Autowired
	UserRepo userrepo;
	
	public Users getuserdetails(long userid) {
		
		return userrepo.findById(userid).orElseThrow(()-> new RuntimeException("user not found exception"));
	}

}
