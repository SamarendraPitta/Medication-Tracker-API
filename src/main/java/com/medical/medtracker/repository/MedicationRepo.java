package com.medical.medtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.medtracker.model.Medication;

@Repository
public interface MedicationRepo extends JpaRepository<Medication, Long>{

}
