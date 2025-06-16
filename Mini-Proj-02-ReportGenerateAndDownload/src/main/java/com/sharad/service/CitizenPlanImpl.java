package com.sharad.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sharad.binding.SearchCriteria;
import com.sharad.entity.CitizenPlan;
import com.sharad.repository.CitizenPlanRepository;
import com.sharad.util.EmailUtils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanImpl implements CitizenPlanService {
				
	  @Autowired
	  private CitizenPlanRepository repository;
	  @Autowired
	  private EmailUtils emailUtils;
	  
	  
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
	public void generateExcel(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<CitizenPlan> allData = repository.findAll();
		// Logic to generate Excel file from allData and write it to response
		HSSFWorkbook workbook= new HSSFWorkbook();
		// Add code to create sheets, rows, and cells in the workbook
		HSSFSheet sheet = workbook.createSheet("Citizen Plans");
		// Add headers and data to the sheet
		HSSFRow headerRow = sheet.createRow(0);
		
		//set data in header row
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("SSN");
		headerRow.createCell(4).setCellValue("Plan Name");
		headerRow.createCell(5).setCellValue("Plan Status");
		
		// Add data rows
		int rowNum = 1;
		
		for(CitizenPlan citizenPlan : allData) {
			// Create a new data row for each citizen plan
			HSSFRow dataRow = sheet.createRow( rowNum);
			// Set data in the data row
			dataRow.createCell(0).setCellValue(citizenPlan.getName());
			dataRow.createCell(1).setCellValue(citizenPlan.getEmail());
			dataRow.createCell(2).setCellValue(citizenPlan.getGender());
			dataRow.createCell(3).setCellValue(citizenPlan.getSsn());
			dataRow.createCell(4).setCellValue(citizenPlan.getPlanName());
			dataRow.createCell(5).setCellValue(citizenPlan.getPlanStatus());
			
			rowNum++;
			
		}
		
		// Write the workbook to the response output stream(To download the Excel file in browser)
			ServletOutputStream outputStream=response.getOutputStream();
			workbook.write(outputStream);
			
			//to send the Excel file as email attachment
			File file =new File("data.xls");
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			emailUtils.sendEmail(file);
			
			// close the workbook 
			workbook.close();
			outputStream.close();
		

	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		
		
		
		//1.servelet output stream to write the PDF content
		ServletOutputStream outputStream = response.getOutputStream();
		
		//2.pdf generation logic- create a PDF document with A4 size
		 Document pdfDoc = new Document(PageSize.A4);
		 PdfWriter.getInstance(pdfDoc, outputStream);
		 pdfDoc.open();
		 
		 
		 File file= new File("data.pdf");
		 Document pdfDoc1 = new Document(PageSize.A4);
	     FileOutputStream fos= new FileOutputStream(file);
	     PdfWriter.getInstance(pdfDoc1, fos);
	     pdfDoc1.open();
		 
		 //3.add content to the PDF document
		        //3.1.add a title to the PDF document 
		 Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	     fontTitle.setSize(14);
	       
	     // title for the PDF document
	     Paragraph title= new Paragraph("Citizen Plans", fontTitle);
	     title.setAlignment(Paragraph.ALIGN_CENTER);
	     pdfDoc.add(title);  // Add the title to the PDF document
	     pdfDoc1.add(title);
	     
	     
	     //3.2.add a line break
	     pdfDoc.add(new Paragraph("  ")); // Add a line break
	     
	     //3.3.add a table to the PDF document
	     PdfPTable table = new PdfPTable(6);
	     table.setWidthPercentage(100f);
	     table.setWidths(new float[] {2.5f, 3.5f, 2.5f, 2.5f, 2.5f, 2.5f });
	     table.setSpacingBefore(10);
	     // pdf cell colors
	     PdfPCell cell = new PdfPCell();
	     cell.setBackgroundColor(CMYKColor.BLUE);
	     cell.setPadding(2);
	     
	     //Add table cell headers
	     cell.setPhrase(new Paragraph("Name",fontTitle));
	     table.addCell(cell);
	     cell.setPhrase(new Paragraph("Email",fontTitle));
	     table.addCell(cell);
	     cell.setPhrase(new Paragraph("Gender",fontTitle));
	     table.addCell(cell);
	     cell.setPhrase(new Paragraph("SSN",fontTitle));
	     table.addCell(cell);
	     cell.setPhrase(new Paragraph("Plan Name",fontTitle));
	     table.addCell(cell);
	     cell.setPhrase(new Paragraph("Plan Status",fontTitle));
	     table.addCell(cell);
	    
	     
	     
	     // 4. fetch data from the database
	     List<CitizenPlan> addData = repository.findAll();
	     // 5. add data to the table
	     for(CitizenPlan cp: addData) {
	    	 table.addCell(cp.getName());
	    	 table.addCell(cp.getEmail());
	    	 table.addCell(cp.getGender());
	    	 table.addCell(String.valueOf(cp.getSsn()));
	    	 table.addCell(cp.getPlanName());
	    	 table.addCell(cp.getPlanStatus());
	     }
	     // add table to pdf
	     pdfDoc.add(table);
	     pdfDoc1.add(table);
	     
			/*   //for email 
			 File file= new File("data.pdf");
			 FileOutputStream fos= new FileOutputStream(file);
			 PdfWriter.getInstance(pdfDoc, fos);
			 emailUtils.sendEmail(file);*/
	     
	     //close the file connection 
	     pdfDoc.close();
	     outputStream.close();
	     pdfDoc1.close();
	     fos.close();
	     emailUtils.sendEmail(file);
	
	}

}
