package com.medical.medtracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compliance{
	
    private double complianceRate;
    private double adjustedRate;
    private int missedCount;
    private int skippedCount;
    private int takenCount;
	public double getComplianceRate() {
		return complianceRate;
	}
	public void setComplianceRate(double complianceRate) {
		this.complianceRate = complianceRate;
	}
	public double getAdjustedRate() {
		return adjustedRate;
	}
	public void setAdjustedRate(double adjustedRate) {
		this.adjustedRate = adjustedRate;
	}
	public int getMissedCount() {
		return missedCount;
	}
	public void setMissedCount(int missedCount) {
		this.missedCount = missedCount;
	}
	public int getSkippedCount() {
		return skippedCount;
	}
	public void setSkippedCount(int skippedCount) {
		this.skippedCount = skippedCount;
	}
	public int getTakenCount() {
		return takenCount;
	}
	public void setTakenCount(int takenCount) {
		this.takenCount = takenCount;
	}
	@Override
	public String toString() {
		return "Compliance [complianceRate=" + complianceRate + ", adjustedRate=" + adjustedRate + ", missedCount="
				+ missedCount + ", skippedCount=" + skippedCount + ", takenCount=" + takenCount + "]";
	}

	
}
