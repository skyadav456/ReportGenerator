package com.sharad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharad.entity.CitizenPlan;

public interface CitizenPlanRepository  extends JpaRepository<CitizenPlan, Integer> {
	
	// Custom query methods can be defined here if needed
	// For example, to find plans by status:
	// List<CitizenPlan> findByPlanStatus(String status);

}
