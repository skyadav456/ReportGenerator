package com.sharad.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.sharad.binding.SearchCriteria;
import com.sharad.entity.CitizenPlan;
import com.sharad.repository.CitizenPlanRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanImpl implements CitizenPlanService {
				
	  @Autowired
	  private CitizenPlanRepository repository;
	  
	  
	@Override
	public List<String> getPlanNames() {
			return repository.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return repository.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getCitizenInfo(SearchCriteria criteria) {
		CitizenPlan citizenPlan = new CitizenPlan();
		if(criteria.getPlanName() != null && !criteria.getPlanName().isEmpty()) {
			citizenPlan.setPlanName(criteria.getPlanName());
		}
		if(criteria.getPlanStatus() != null && !criteria.getPlanStatus().isEmpty()) {
			citizenPlan.setPlanStatus(criteria.getPlanStatus());
		}
		if(criteria.getGender() != null && !criteria.getGender().isEmpty()) {
			citizenPlan.setGender(criteria.getGender());
			}
		//QEB means Query by Example - it is a way to create a query based on an example entity
		  Example<CitizenPlan> example = Example.of(citizenPlan);
		
		return repository.findAll(example);
	}

	@Override
	public void generateExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generatePdf(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
