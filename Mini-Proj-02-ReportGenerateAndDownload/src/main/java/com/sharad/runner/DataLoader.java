package com.sharad.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sharad.entity.CitizenPlan;
import com.sharad.repository.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	    @Autowired
	      CitizenPlanRepository citizenPlanRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		citizenPlanRepository.deleteAll();     // Clear existing data before loading new data
		
		// Create and save CitizenPlan objects
		CitizenPlan plan1 = new CitizenPlan("John Doe", "john@gmail.com", 1345L, "Male", 222555L, "Cash", "Approved", LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan plan2 = new CitizenPlan("Alice Smith", "alice@gmail.com", 2345L, "Female", 333666L, "Food", "Denied", null, null);
		CitizenPlan plan3 = new CitizenPlan("Bob Johnson", "bob@gmail.com", 3456L, "Male", 444777L, "Medical", "Approved", LocalDate.now(), LocalDate.now().plusMonths(12));
		CitizenPlan plan4 = new CitizenPlan("Mary Jane", "mary@gmail.com", 4567L, "Female", 555888L, "Food", "Approved", LocalDate.now(), LocalDate.now().plusMonths(2));
		CitizenPlan plan5 = new CitizenPlan("David Warner", "david@gmail.com", 5678L, "Male", 666999L, "Food", "Approved", LocalDate.now(), LocalDate.now().plusMonths(5));
		CitizenPlan plan6 = new CitizenPlan("Emma Watson", "emma@gmail.com", 6789L, "Female", 777000L, "Employment", "Approved", LocalDate.now(), LocalDate.now().plusMonths(6));
		CitizenPlan plan7 = new CitizenPlan("Robert Brown", "robert@gmail.com", 7890L, "Male", 888111L, "Medical", "Denied", null, null);
		CitizenPlan plan8 = new CitizenPlan("Olivia Davis", "olivia@gmail.com", 8901L, "Female", 999222L, "Cash", "Approved", LocalDate.now(), LocalDate.now().plusMonths(8));
		CitizenPlan plan9 = new CitizenPlan("Liam Wilson", "liam@gmail.com", 9012L, "Male", 111333L, "Employment", "Approved", LocalDate.now(), LocalDate.now().plusMonths(3));
		CitizenPlan plan10 = new CitizenPlan("Sophia Lee", "sophia@gmail.com", 1234L, "Female", 222444L, "Food", "Denied", null, null);
		
		List<CitizenPlan> planList = Arrays.asList(plan1, plan2, plan3, plan4, plan5, plan6, plan7, plan8, plan9, plan10);
		
		citizenPlanRepository.saveAll(planList);
		

	}

}
