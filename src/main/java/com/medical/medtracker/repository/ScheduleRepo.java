package com.medical.medtracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medical.medtracker.model.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long>{

	@Query("SELECT s FROM Schedule s WHERE s.user_id = :userId AND s.medication_id = :medicationId")
	Optional<Schedule> findByUserIdAndMedicationId(
	    @Param("userId") Long userId,
	    @Param("medicationId") Long medicationId
	);


}
