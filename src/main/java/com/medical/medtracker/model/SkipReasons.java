package com.medical.medtracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SkipReasons {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long intake_id;
	private String reason_type;
	private String specific_reason;
	private String authorized_by;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIntake_id() {
		return intake_id;
	}
	public void setIntake_id(long intake_id) {
		this.intake_id = intake_id;
	}
	public String getReason_type() {
		return reason_type;
	}
	public void setReason_type(String reason_type) {
		this.reason_type = reason_type;
	}
	public String getSpecific_reason() {
		return specific_reason;
	}
	public void setSpecific_reason(String specific_reason) {
		this.specific_reason = specific_reason;
	}
	public String getAuthorized_by() {
		return authorized_by;
	}
	public void setAuthorized_by(String authorized_by) {
		this.authorized_by = authorized_by;
	}
	@Override
	public String toString() {
		return "SkipReasons [id=" + id + ", intake_id=" + intake_id + ", reason_type=" + reason_type
				+ ", specific_reason=" + specific_reason + ", authorized_by=" + authorized_by + "]";
	}
	
	
	
}
