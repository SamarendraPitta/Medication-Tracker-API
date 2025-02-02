package com.medical.medtracker.model;

import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String brand_name;
	private String dosage_form;
	private String strength;
	private String manufacturer;
	
	@Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;
	 
	 public Medication() {};
	 
	 
	 public long getId() {
		 return id;
	 }
	 
	 public void setId(long id) {
		 this.id = id;
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getDosage_form() {
		return dosage_form;
	}

	public void setDosage_form(String dosage_form) {
		this.dosage_form = dosage_form;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
	
	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", brand_name=" + brand_name + ", dosage_form=" + dosage_form
				+ ", strength=" + strength + ", manufacturer=" + manufacturer + ", metadata=" + metadata + "]";
	}
}
