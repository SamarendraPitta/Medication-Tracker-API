package com.medical.medtracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.medtracker.model.Schedule;
import com.medical.medtracker.repository.ScheduleRepo;

@Service
public class Scheduleservice {

	@Autowired
	ScheduleRepo schedulerepo;
	
	public Schedule getscheduledetails(Long scheduleid) {
        // Add debug logging
        System.out.println("passing id : " + scheduleid);
        Optional<Schedule> schedule = schedulerepo.findById(scheduleid);
        System.out.println("Found schedule: " + schedule);
        return schedule.orElseThrow(() -> new RuntimeException("given scheduleid not found"));
    }

}
