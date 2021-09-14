package com.techgroup.dao;

import java.sql.SQLException;
import java.util.Scanner;

import com.techgroup.delegate.PersonsDelegate;

public class PersonDAO {
	Scanner scan = new Scanner(System.in);
	int id;
	String fname,lname,street,city,state,zipcode;
	PersonVO pvo = new PersonVO();
	PersonsDelegate pd = new PersonsDelegate();
	

	public void addPerson(){
		System.out.println("enter id");
		id = scan.nextInt();
		pvo.setId(id);
		System.out.println("enter the First Name");
		pvo.setFirstName(scan.next());
		System.out.println("Enter the Last Name");
		pvo.setLastName(scan.next());
		pd.addPerson(pvo);
		
		
	}

	public void editPerson(){
		String fname = null;
		String lname = null;
		System.out.println("Enter peron id to edit");
		pvo.setId(scan.nextInt());
		System.out.println("enter your first name");
		pvo.setFirstName(scan.next());
		System.out.println("Enter your last name");
		pvo.setLastName(scan.next());
		
		System.out.println("Please enter to edit F for first name , L for last name, and B for full anme");
		String str = scan.next();
		if(str.equalsIgnoreCase("f")){
			System.out.println("enter the new first name");
			fname = scan.next();
					
		}
		else if(str.equalsIgnoreCase("l")){
			System.out.println("Enter the new last name ");
			lname = scan.next();
			
		}
		else if(str.equalsIgnoreCase("b")){
			System.out.println("enter the new first name");
			fname = scan.next();
			System.out.println("Enter the new last name ");
			lname = scan.next();
		}
		else 
		{
			System.out.println("Invalid Input");
		}
				
		pd.editPerson(pvo,fname,lname);
	}
	
	public void deletePerson(){
		
		System.out.println("Enter ID to Delete record");
		pvo.setId(scan.nextInt());
		pd.deletePerson(pvo);
	}
	
	public void addAddress(){
		
		System.out.println("Enter the Person ID to add Address");
		pvo.setId(scan.nextInt());
		System.out.println("Enter the Street Name");
		pvo.setStreet(scan.next());
		System.out.println("Enter the City Name");
		pvo.setCity(scan.next());
		System.out.println("Enter the State Name");
		pvo.setState(scan.next());
		System.out.println("Enter the Zipcode");
		pvo.setZipcode(scan.next());
		pd.addAddress(pvo);
	}
	
	
	public void editAddress(){
		
		addAddress();
	}
	
	public void deleteAddress(){
		
		System.out.println("Enter Person ID to delete Address");
		pvo.setId(scan.nextInt());
		pd.deleteAddress(pvo);
	}
	
	public void displayPersonList(){
	
		String query="select * from person";
		pd.displayPersonList(query);
	}
	
	public void totalPersons(){
		System.out.println("Total Number of records "+ pd.totalPersons());
	}
}
