package com.techgroup.service;

import java.util.Scanner;

import com.techgroup.dao.PersonDAO;

public class PersonService {
	
	Scanner scan = new Scanner(System.in);
	PersonDAO pdao = new PersonDAO();
public void options() {
		
		System.out.println("Please choose one of the option");
		System.out.println("Press 1:Add Person");
		System.out.println("Press 2:Edit Person");
		System.out.println("Press 3:Delete Person");
		System.out.println("Press 4: Add Address to Person");
		System.out.println("Press 5: Edit Address");
		System.out.println("Press 6: Delete Address");
		System.out.println("Press 7: Display Total Number of Persons");
		System.out.println("Press 8: Display List of Persons");
		
		int opt = scan.nextInt();
		
	switch (opt){
	
	case 1:
		pdao.addPerson();
		options();
		break;
	case 2:
		pdao.editPerson();
		options();
		break;
	case 3:
		pdao.deletePerson();
		options();
		break;
	case 4:
		pdao.addAddress();
		options();
		break;
	case 5:
		pdao.editAddress();
		options();
		break;
	case 6:
		pdao.deleteAddress();
		options();
		break;
	case 7:
		pdao.totalPersons();
		options();
		break;
	case 8:
		pdao.displayPersonList();
		options();
		break;
	default:
		System.out.println("Invalid input");
	}
}
}
