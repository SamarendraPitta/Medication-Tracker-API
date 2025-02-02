package com.medical.medtracker.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.medtracker.model.Compliance;
import com.medical.medtracker.model.Intake;
import com.medical.medtracker.model.Schedule;
import com.medical.medtracker.repository.IntakeRepo;
import com.medical.medtracker.repository.ScheduleRepo;

@Service
@Transactional(readOnly = true)
public class Complianceservice {
    
    @Autowired
    private ScheduleRepo scheduleRepo;
    
    @Autowired
    private IntakeRepo intakeRepo;
    
    public Compliance calculateComplianceRate(
            Long userId, 
            Long medicationId, 
            LocalDate startDate, 
            LocalDate endDate) {
            
    	Schedule schedule = scheduleRepo.findByUserIdAndMedicationId(userId, medicationId)
    		    .orElseThrow(() -> new RuntimeException("Schedule not found for user " + userId + " and medication " + medicationId));
        
    	//System.out.println(schedule.getDays_of_week());
    	
        // Calculate expected doses based on schedule pattern
        int totalScheduledDoses = calculateTotalDoses(startDate, endDate, schedule.getDays_of_week());
        System.out.println("tsd :" + totalScheduledDoses);
        
        // Get actual intakes
        List<Intake> intakes = intakeRepo.findByUserIdAndDateRange(
            schedule.getId(),
            userId
        );
        
        // Count by status
        Map<String, Long> statusCounts = intakes.stream()
            .collect(Collectors.groupingBy(
                Intake::getStatus,
                Collectors.counting()
            ));
            
        int takenCount = statusCounts.getOrDefault("TAKEN", 0L).intValue();
        int skippedCount = statusCounts.getOrDefault("SKIPPED", 0L).intValue();
        int missedCount = statusCounts.getOrDefault("MISSED", 0L).intValue();
        
        // Calculate rates
        double complianceRate = calculateRate(takenCount, totalScheduledDoses);
        double adjustedRate = calculateAdjustedRate(takenCount, totalScheduledDoses, skippedCount);
        
        System.out.println("complaince :" + complianceRate + "takencount :" + takenCount);
       
        return Compliance.builder()
            .complianceRate(roundToTwoDecimals(complianceRate))
            .adjustedRate(roundToTwoDecimals(adjustedRate))
            .missedCount(missedCount)
            .skippedCount(skippedCount)
            .takenCount(takenCount)
            .build();
    }
    
    private int calculateTotalDoses(LocalDate startDate, LocalDate endDate, List<Integer> daysOfWeek) {
        int totalDoses = 0;
        LocalDate currentDate = startDate;
        
        //System.out.println("startdate :" + startDate + "lastdate :" +endDate + "days of week :" +daysOfWeek);
        
        while (!currentDate.isAfter(endDate)) {
            if (daysOfWeek.contains(currentDate.getDayOfWeek().getValue())) {
                totalDoses++;
            }
            currentDate = currentDate.plusDays(1);
        }
        
        return totalDoses;
    }

    
    private double calculateRate(int taken, int total) {
        if (total == 0) return 0.0;
        System.out.println("Calculating rate: " + taken + "/" + total);
        return (double) taken / total;
    }

    private double calculateAdjustedRate(int taken, int total, int skipped) {
        int adjustedTotal = total - skipped;
        if (adjustedTotal == 0) return 0.0;
        System.out.println("Calculating adjusted rate: " + taken + "/" + adjustedTotal);
        return (double) taken / adjustedTotal;
    }

    
    private double roundToTwoDecimals(double value) {
        return BigDecimal.valueOf(value)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    }
}
