package com.theatresection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TheatreSectionApplication {

	public static void main(String[] args) {
		
		int totalNumOfSeats = 0;
		List <Section> sectionList = null;
		List <Row> rowList = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the number of rows ");
		
		int numRows = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0 ; i < numRows ; i++) {
			sectionList = new ArrayList<>();
			Row row = new Row();
			row.setRowNum(i + 1);
			String seatRow = scanner.nextLine();
			String [] rowArray = seatRow.split(" ");
			for (int j = 0 ; j < rowArray.length ; j++ ) {
				Section sec = new Section();
				sec.setSecId(j + 1);
				sec.setSeatCount(Integer.parseInt(rowArray[j]));
				totalNumOfSeats = totalNumOfSeats + Integer.parseInt(rowArray[j]);
				sectionList.add(sec);
			}
			row.setSectionList(sectionList);
			rowList.add(row);			
		}
		
		List<Customer> customerList = new ArrayList<>();
		
		System.out.println("Enter the person name followed by space and seats ");
		
		while (scanner.hasNextLine()){
			String str = scanner.nextLine();
			String [] str1 = str.split(" ");
			
			Customer cust = new Customer();
			cust.setPersonName(str1[0]);
			cust.setNumSeatRequested(Integer.parseInt(str1[1]));
			customerList.add(cust);
		}
		scanner.close();
		
		for(Customer cust : customerList) {
			int count = 0;
			for (Row row : rowList) {
				count ++;
				for (Section sect : row.getSectionList()) {
					int seats = sect.getSeatCount();
					if(cust.getNumSeatRequested() > 0 && seats == cust.getNumSeatRequested() && count  <= 2) {
						sect.setSeatCount(sect.getSeatCount() - cust.getNumSeatRequested());
						totalNumOfSeats = totalNumOfSeats - cust.getNumSeatRequested();
						cust.setNumSeatRequested(0);
					}
				}
			}
			
			for ( Row row : rowList) {
				for (Section sect : row.getSectionList()) {
					int seats = sect.getSeatCount();
					if(cust.getNumSeatRequested() > 0 && seats >= cust.getNumSeatRequested()) {
						sect.setSeatCount(sect.getSeatCount() - cust.getNumSeatRequested());
						totalNumOfSeats = totalNumOfSeats - cust.getNumSeatRequested();
						cust.setNumSeatRequested(0);
					}
				}
			}
			
			if(cust.getNumSeatRequested() > 0 && totalNumOfSeats >= cust.getNumSeatRequested()) {
				System.out.println(cust.getNumSeatRequested() + " call to split party");
			}else if (cust.getNumSeatRequested() > 0 && totalNumOfSeats < cust.getNumSeatRequested()) {
				System.out.println(cust.getNumSeatRequested() + "Sorry, we can't handle your party");
			}
		}
		
	}

}
