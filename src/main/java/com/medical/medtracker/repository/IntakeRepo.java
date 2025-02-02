package com.medical.medtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medical.medtracker.model.Intake;

@Repository
public interface IntakeRepo extends JpaRepository<Intake, Long> {
	@Query("SELECT i FROM Intake i " +
		       "WHERE i.user_id = :userId " +
		       "AND i.schedule_id = :id")

	List<Intake> findByUserIdAndDateRange(long id, Long userId);

}



