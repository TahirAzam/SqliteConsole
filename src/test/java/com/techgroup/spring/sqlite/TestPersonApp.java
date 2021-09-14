package com.techgroup.spring.sqlite;

import org.junit.Test;

import com.techgroup.dao.PersonVO;
import com.techgroup.delegate.PersonsDelegate;

import junit.framework.TestCase;

public class TestPersonApp  extends TestCase {
	
	PersonsDelegate delegate ;
	PersonVO pvo = new PersonVO();
	
	public void setUp(){
			
	        delegate = new PersonsDelegate();
	        delegate.connection();	
	        }
	

	    @Test
	    public void testAddPerson() {
	       setUp();
	       pvo.setId(5);
	       pvo.setFirstName("Tahir");
	       pvo.setLastName("Azam");
	       delegate.addPerson(pvo);
	       assertEquals(delegate.getDataById(5),"Tahir");
	       System.out.println("Test Person Record added successfully");
	       
	    }
	    
	
		@Test
		public void testEditPerson(){
			setUp();
			pvo.setId(2);
			pvo.setFirstName("Tahir");
			pvo.setLastName("Azam");
			String fname = "Talha";
			String lname = "Tahir";
			delegate.editPerson(pvo, fname, lname);
			assertEquals(delegate.getDataById(2),"Talha");
			System.out.println("Test Person Record Edited Successfully");
					
		}
		
	
		@Test
		public void testDeletePerson(){
		    pvo.setId(4);
		    delegate.deletePerson(pvo);
		    assertEquals(delegate.getDataById(4),null);
		    System.out.println("Test Person Record Deleted successfully");
		}
		
		@Test
		public void testAddAddress(){
			pvo.setId(1);
			pvo.setStreet("oconnell");
			pvo.setCity("dublin");
			pvo.setState("leinster");
			pvo.setZipcode("d1");
			delegate.addAddress(pvo);
			assertEquals(delegate.getAddressById(1),"oconnell");
			System.out.println("Test Address Added Successfully");
		}
		
		@Test
		public void testDeleteAddress(){
			pvo.setId(1);
			delegate.deleteAddress(pvo);
			assertEquals(delegate.getAddressById(1),"oconnell");
			System.out.println("Test Address Deleted Successfully");
		}
		
		public void testPersonCount(){
			pvo.setId(6);
			pvo.setFirstName("Tahir");
			pvo.setLastName("Azam");
			delegate.addPerson(pvo);
			int count = delegate.totalPersons();
			assertTrue(count>0);
		}
		
	}
