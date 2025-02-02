package com.medical.medtracker.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	    
    private long medication_id;
    private long user_id;

    @Column(columnDefinition = "TIME")
    private LocalTime scheduled_time;   
    
    @Column(name = "days_of_week", columnDefinition = "integer[]")
    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<Integer> days_of_week = new ArrayList<>();


    @Column(columnDefinition = "DATE") 
    private LocalDate start_date;

    @Column(columnDefinition = "DATE") 
    private LocalDate end_date;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMedication_id() {
		return medication_id;
	}

	public void setMedication_id(long medication_id) {
		this.medication_id = medication_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public LocalTime getScheduled_time() {
		return scheduled_time;
	}

	public void setScheduled_time(LocalTime schedule_time) {
		this.scheduled_time = schedule_time;
	}

	public List<Integer> getDays_of_week() {
		return days_of_week;
	}

	public void setDays_of_week(List<Integer> days_of_week) {
		this.days_of_week = days_of_week;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", medication_id=" + medication_id + ", user_id=" + user_id + ", schedule_time="
				+ scheduled_time + ", days_of_week=" + days_of_week + ", start_date=" + start_date + ", end_date="
				+ end_date + ", metadata=" + metadata + "]";
	}
    
    

}