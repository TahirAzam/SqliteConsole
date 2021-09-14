package com.techgroup.delegate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.techgroup.dao.PersonVO;
public class PersonsDelegate {
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	public Connection connection(){
		
		try{
			 con = DriverManager.getConnection("jdbc:sqlite:techgroup.db");
			 stmt=con.createStatement();
			
			 stmt.executeUpdate("create table if not exists person("
			 		+ "id integer primary key,"
			 		+ "first_name text," 
			 		+ "last_name text,"
			 		+ "street text,"
			 		+ "city text,"
			 		+ "state text,"
			 		+ "zipcode text)");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return con;
		
	}
	public void disconnect() throws SQLException{
		con.close();
		stmt.close();
		
	}
	public void  addPerson(PersonVO pvo){
	try{	
		connection();
		if(checkIdExist(pvo.getId())){
			System.out.println("Id already exist");
			return;
		}
		else{
		String query = "insert into person (id,first_name,last_name) values("
				+ "'"+pvo.getId()+"',"
				+ "'"+pvo.getFirstName()+"',"
				+ "'"+pvo.getLastName()+"')";
		stmt.executeUpdate(query);
		System.out.println("person added successfully");
		disconnect();
		}
		
	}
	catch(SQLException e){
		System.out.println(e.getMessage());
	}
	
	}
	
	public void editPerson(PersonVO pvo, String fname, String lname){
		
		try{
			connection();
			rs = stmt.executeQuery("select * from person  where id = '"
					+ ""+ pvo.getId() +"' and  first_name = '"+pvo.getFirstName()+"'"
					+ "and last_name = '" + pvo.getLastName()+"'");
			if(rs.equals(null)){
				System.out.println("Record does not exists");
				}
			
			else{
				if(fname != null && lname == null){
					stmt.executeUpdate("update person set first_name = '"+fname+"' where id = '"+pvo.getId()+"'");
			
					}
				else if(lname != null  &&  fname == null){
					stmt.executeUpdate("update person set last_name = '"+lname+"' where id = '"+pvo.getId()+"'");
					}
				else{
					stmt.executeUpdate("update person set first_name ='"+fname+"' , last_name = '"+lname+"' where id = '"+pvo.getId()+"'");
					}
			}
			disconnect();
		}
		
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void displayPersonList(String query){
		try{
			connection();
			rs = stmt.executeQuery(query);
			System.out.println("ID \t"
				+ "First Name \t"
				+ "Last Name \t"
				+ "Street \t\t"
				+ "City \t\t"
				+ "State \t\t"
				+ "Zipcode");
				
		while(rs.next()){
			System.out.println(rs.getString("id" )+ "\t" 
					+ rs.getString("first_name")+ "\t\t"
					+ rs.getString("last_name")+ "\t\t" 
					+ rs.getString("street")+ "\t\t" 
					+ rs.getString("city")+ "\t\t" 
					+ rs.getString("state")+ "\t\t" 
					+ rs.getString("zipcode"));  
		}
		disconnect();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deletePerson(PersonVO pvo) {
		
		try{
			connection();
			stmt.executeUpdate("delete from person where id = '"+pvo.getId()+"'");
			System.out.println("Record successfully deleted");
			disconnect();
		
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

		
	}
	public void addAddress(PersonVO pvo) {
		
		connection();
		try {
		rs = stmt.executeQuery("select * from person where id = '" + pvo.getId() + "'");
		if (!rs.isBeforeFirst() ){
			System.out.println("No Record");
		}
		else
		stmt.executeUpdate("update person set "
				+ "street = '"+ pvo.getStreet() +"' , "
				+ "city  = '"+ pvo.getCity() +"' , "
				+ "state = '"+ pvo.getState() +"' , "
				+ "zipcode = '"+ pvo.getZipcode() +"' "
				+ " where id = '" +pvo.getId()+"'"
				);
		disconnect();	
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public void deleteAddress(PersonVO pvo) {
		
		
		try{
			connection();
			stmt.executeUpdate("update person set "
					+ "street = 'null',"
					+ "city = 'null',"
					+ "state = 'null',"
					+ "zipcode = 'null'"
					+ " where id = '"+pvo.getId()+"'");
			System.out.println("Address deleted for id: " + pvo.getId());
			disconnect();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public int totalPersons() {
		int count = 0;
		try{
		connection();
		
		//rs = stmt.executeQuery("select count(*) from person");
		rs = stmt.executeQuery("select max(id) from person");
		count = rs.getInt(1);
		disconnect();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return count;
	}
	
	public String getDataById(int id){
		String res= null ,sql;
		try {
			sql = "select * from person where id = " + id;
			connection();
			rs= stmt.executeQuery(sql);
			res = rs.getString(2);
			disconnect();
			return res;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		
		return res;	
	}
	
	public boolean checkIdExist(int id){
		try{
			connection();
			rs = stmt.executeQuery("select id from person where id = " + id);
			if(rs.next()){
				disconnect();
				return true;
			}
			else
				return false;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	public String getAddressById(int id) {
		try{
			connection();
			rs = stmt.executeQuery("select *from person where id = " + id);
		 return rs.getString(4);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
