package com.medical.medtracker.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovingAverages {

	List<String> dates;
    List<Double> values;
    String type;
    int period;
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<Double> getValues() {
		return values;
	}
	public void setValues(List<Double> values) {
		this.values = values;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	@Override
	public String toString() {
		return "MovingAverages [dates=" + dates + ", values=" + values + ", type=" + type + ", period=" + period + "]";
	}
	
	
}
