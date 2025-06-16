package com.sharad.service;

import java.util.List;
import com.sharad.binding.SearchCriteria;
import com.sharad.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;


public interface CitizenPlanService {

	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<CitizenPlan> getCitizenInfo(SearchCriteria criteria);
	public void generateExcel(HttpServletResponse response) throws Exception;
	public void generatePdf(HttpServletResponse response) throws Exception;

}
