package com.medical.medtracker.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.medtracker.model.Intake;
import com.medical.medtracker.model.Schedule;
import com.medical.medtracker.repository.IntakeRepo;
import com.medical.medtracker.repository.ScheduleRepo;

@Service
public class Intakeservice {

    @Autowired
    private IntakeRepo intakerepo;
    
    @Autowired
    private ScheduleRepo schrepo;
    
    public Intake recordintakes(Intake intakereq) {
        Schedule schedule = schrepo.findById(intakereq.getSchedule_id())
                                    .orElseThrow(()-> new RuntimeException("Given ScheduleID not found"));
        Intake intake = new Intake();
        
        // Convert LocalTime to LocalDateTime by combining with current date
        LocalDateTime scheduledDateTime = LocalDateTime.of(
            LocalDateTime.now().toLocalDate(),
            schedule.getScheduled_time()
        );
        
        intake.setSchedule_id(intakereq.getSchedule_id());
        intake.setUser_id(schedule.getUser_id());
        intake.setStatus(intakereq.getStatus());
        intake.setScheduled_for(scheduledDateTime);  // Now passing LocalDateTime
        intake.setTaken_at(intakereq.getStatus().equals("TAKEN") ? LocalDateTime.now() : null);
        //intake.setMetadata("{}");
        return intakerepo.save(intake);
    }
}

