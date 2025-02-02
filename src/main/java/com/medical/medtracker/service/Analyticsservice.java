package com.medical.medtracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.medical.medtracker.model.Compliance;
import com.medical.medtracker.model.MovingAverages;
import com.medical.medtracker.model.Schedule;
import com.medical.medtracker.repository.ScheduleRepo;

@Service
public class Analyticsservice {
	
    @Autowired
    private Complianceservice complianceService;
    
    @Autowired
    private ScheduleRepo scheduleRepo;
    
    @Cacheable(value = "movingAverages", 
            key = "{#userId, #medicationId, #type, #days}")
    
    public MovingAverages calculateMovingAverages(Long userId, Long medicationId, 
                                                         String type, int days) {
    	
    	Schedule schedule = scheduleRepo.findByUserIdAndMedicationId(userId, medicationId)
    		    .orElseThrow(() -> new RuntimeException("Schedule not found for user " + userId + " and medication " + medicationId));
    	
        LocalDate endDate = schedule.getEnd_date();
        LocalDate startDate = schedule.getStart_date();
        
        List<DailyCompliance> complianceData = getDailyComplianceRates(
            userId, medicationId, startDate, endDate
        );
        
        List<Double> values = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();

        switch (type.toUpperCase()) {
            case "SMA" -> calculateSMA(complianceData, days, values, dates);
            case "EMA" -> calculateEMA(complianceData, days, values, dates);
            case "WMA" -> calculateWMA(complianceData, days, values, dates);
            default -> throw new IllegalArgumentException("Invalid moving average type");
        }

        return new MovingAverages(
                dates.stream().map(LocalDate::toString).toList(),
                values,
                type.toUpperCase(),
                days
            );
    }

    private List<DailyCompliance> getDailyComplianceRates(Long userId, Long medicationId, 
                                                          LocalDate start, LocalDate end) {
        List<DailyCompliance> rates = new ArrayList<>();
        
        LocalDate current = start;
        while (!current.isAfter(end)) {
            Compliance compliance = complianceService.calculateComplianceRate(
                userId, medicationId, current, current
            );
            rates.add(new DailyCompliance(current, compliance.getComplianceRate()));
            current = current.plusDays(1);
        }
        
        return rates;
    }

    // Simple Moving Average implementation
    private void calculateSMA(List<DailyCompliance> data, int period, 
                              List<Double> values, List<LocalDate> dates) {
        for (int i = period-1; i < data.size(); i++) {
            double sum = 0;
            for (int j = 0; j < period; j++) {
                sum += data.get(i-j).rate();
            }
            values.add(round(sum / period));
            dates.add(data.get(i).date());
        }
    }

    // Exponential Moving Average implementation
    private void calculateEMA(List<DailyCompliance> data, int period,
                             List<Double> values, List<LocalDate> dates) {
        double smoothingFactor = 2.0 / (period + 1);
        double ema = data.get(period-1).rate();

        values.add(round(ema));
        dates.add(data.get(period-1).date());

        for (int i = period; i < data.size(); i++) {
            ema = data.get(i).rate() * smoothingFactor + ema * (1 - smoothingFactor);
            values.add(round(ema));
            dates.add(data.get(i).date());
        }
    }

    // Weighted Moving Average implementation
    private void calculateWMA(List<DailyCompliance> data, int period,
                              List<Double> values, List<LocalDate> dates) {
        int totalWeight = period * (period + 1) / 2;
        
        for (int i = period-1; i < data.size(); i++) {
            double weightedSum = 0;
            for (int j = 0; j < period; j++) {
                weightedSum += data.get(i-j).rate() * (j+1);
            }
            values.add(round(weightedSum / totalWeight));
            dates.add(data.get(i).date());
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private record DailyCompliance(LocalDate date, double rate) {}
}
