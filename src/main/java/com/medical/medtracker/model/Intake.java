package com.medical.medtracker.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Intake {

	public Intake() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long schedule_id;
    private long user_id;
    private String status;

    @Column(name = "scheduled_for", columnDefinition = "TIMESTAMP")
    private LocalDateTime scheduled_for;

    @Column(name = "taken_at", columnDefinition = "TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime taken_at;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(long schedule_id) {
		this.schedule_id = schedule_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getScheduled_for() {
		return scheduled_for;
	}

	public void setScheduled_for(LocalDateTime scheduled_for) {
		this.scheduled_for = scheduled_for;
	}

	public LocalDateTime getTaken_at() {
		return taken_at;
	}

	public void setTaken_at(LocalDateTime taken_at) {
		this.taken_at = taken_at;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Intake [id=" + id + ", schedule_id=" + schedule_id + ", user_id=" + user_id + ", status=" + status
				+ ", scheduled_for=" + scheduled_for + ", taken_at=" + taken_at + ", metadata=" + metadata + "]";
	}
    
    
}
