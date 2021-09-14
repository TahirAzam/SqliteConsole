package com.techgroup.spring.sqlite;

import org.springframework.boot.SpringApplication;

import java.sql.SQLException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techgroup.dao.PersonDAO;
import com.techgroup.delegate.PersonsDelegate;
import com.techgroup.service.PersonService;

@SpringBootApplication
public class PersonApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}
	
@Override
public void run(String... args ) {
	PersonService ps = new PersonService();
	ps.options();
}
}
