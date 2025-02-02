package com.medical.medtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.medtracker.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{

}
