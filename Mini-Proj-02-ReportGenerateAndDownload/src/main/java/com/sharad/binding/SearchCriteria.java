package com.sharad.binding;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SearchCriteria {
	
	private String planName;
	private String planStatus;
	private String Gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate planStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate planEndDate;

}
