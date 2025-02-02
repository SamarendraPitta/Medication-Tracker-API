package com.medical.medtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.medtracker.model.SkipReasons;

@Repository
public interface SkipReasonsRepo extends JpaRepository<SkipReasons, Long> {

}
