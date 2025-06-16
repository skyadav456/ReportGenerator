package com.sharad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sharad.binding.SearchCriteria;
import com.sharad.entity.CitizenPlan;
import com.sharad.service.CitizenPlanService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CitizenPlanController {
	
	@Autowired
	private CitizenPlanService service;
	
	@GetMapping("/")
	public String index(Model model) {
		 formInit(model);
		return "index"; // Return the name of the view (index.html or index.jsp)
	}

	@PostMapping("/filter-data")
	public String handleSearch(@ModelAttribute("searchCriteria") SearchCriteria criteria, Model model) {
		
		       List<CitizenPlan> citizenInfo = service.getCitizenInfo(criteria);
		       	model.addAttribute("citizenInfo", citizenInfo);
				formInit(model);
				System.out.println(criteria);
		return "index"; 
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=citizen-plans.xls"); // Set the header (key,value)for file download
		// Call the service method to generate the Excel file
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=data.pdf"); // Set the header (key,value)for file download
		// Call the service method to generate the Excel file
		service.generatePdf(response);
	}
	
	
	private void formInit(Model model) {
		List<String> planNames = service.getPlanNames();
		 List<String> planStatus = service.getPlanStatus();
		 model.addAttribute("planNameList", planNames);
		 model.addAttribute("planStatusList", planStatus);
		 model.addAttribute("searchCriteria", new SearchCriteria());
	}
}
