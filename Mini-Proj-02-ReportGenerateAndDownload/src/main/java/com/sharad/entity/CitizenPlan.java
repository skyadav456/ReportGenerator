package com.sharad.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CitizenPlan {
		
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer citizenId;
	    
	    private String name;
	    private String email;
	    private Long phno;
	    private String gender;
	    private Long ssn;
	    private String planName;
	    private String planStatus;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate planStartDate;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate planEndDate;
	    
	    
		public CitizenPlan(String name, String email, Long phno, String gender, Long ssn, String planName,
				String planStatus, LocalDate planStartDate, LocalDate planEndDate) {
			super();
			this.name = name;
			this.email = email;
			this.phno = phno;
			this.gender = gender;
			this.ssn = ssn;
			this.planName = planName;
			this.planStatus = planStatus;
			this.planStartDate = planStartDate;
			this.planEndDate = planEndDate;
		}
		public Integer getCitizenId() {
			return citizenId;
		}
		public void setCitizenId(Integer citizenId) {
			this.citizenId = citizenId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Long getPhno() {
			return phno;
		}
		public void setPhno(Long phno) {
			this.phno = phno;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Long getSsn() {
			return ssn;
		}
		public void setSsn(Long ssn) {
			this.ssn = ssn;
		}
		public String getPlanName() {
			return planName;
		}
		public void setPlanName(String planName) {
			this.planName = planName;
		}
		public String getPlanStatus() {
			return planStatus;
		}
		public void setPlanStatus(String planStatus) {
			this.planStatus = planStatus;
		}
		public LocalDate getPlanStartDate() {
			return planStartDate;
		}
		public void setPlanStartDate(LocalDate planStartDate) {
			this.planStartDate = planStartDate;
		}
		public LocalDate getPlanEndDate() {
			return planEndDate;
		}
		public void setPlanEndDate(LocalDate planEndDate) {
			this.planEndDate = planEndDate;
		}
		
		
		@Override
		public String toString() {
			return "CitizenPlan [citizenId=" + citizenId + ", name=" + name + ", email=" + email + ", phno=" + phno
					+ ", gender=" + gender + ", ssn=" + ssn + ", planName=" + planName + ", planStatus=" + planStatus
					+ ", planStartDate=" + planStartDate + ", planEndDate=" + planEndDate + "]";
		}
		
	    
	    
	    
	    
}
