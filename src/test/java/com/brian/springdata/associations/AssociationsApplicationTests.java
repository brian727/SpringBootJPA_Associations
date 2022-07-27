package com.brian.springdata.associations;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.brian.springdata.associations.manytomany.entities.Programmer;
import com.brian.springdata.associations.manytomany.entities.Project;
import com.brian.springdata.associations.manytomany.repos.ProgrammerRepository;
import com.brian.springdata.associations.onetomany.entities.Customer;
import com.brian.springdata.associations.onetomany.entities.PhoneNumber;
import com.brian.springdata.associations.onetomany.repos.CustomerRepository;
import com.brian.springdata.associations.onetoone.entities.License;
import com.brian.springdata.associations.onetoone.entities.Person;
import com.brian.springdata.associations.onetoone.repos.LicenseRepository;

@SpringBootTest
class AssociationsApplicationTests {
	
	@Autowired
	CustomerRepository repo;
	@Autowired
	ProgrammerRepository programrepo;
	@Autowired
	LicenseRepository licrepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateCustomer() {
		
		Customer customer = new Customer();
		customer.setName("brian");
		
		PhoneNumber ph1 = new PhoneNumber();
		ph1.setNumber("123-456-7890");
		ph1.setType("mobile");
		
		PhoneNumber ph2 = new PhoneNumber();
		ph2.setNumber("222-222-2222");  
		ph2.setType("home");
	
		customer.addPhoneNumber(ph1);
		customer.addPhoneNumber(ph2);
		
		repo.save(customer);
	}
	
	@Test
	@Transactional
	public void testLoadCustomer() {
		Customer customer = repo.findById(2L).get();
		System.out.println(customer.getName());
		
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(number -> System.out.println(number.getNumber()));
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = repo.findById(1L).get();
		customer.setName("Rockdoggy");
		
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(number -> number.setType("mobile"));
		
		repo.save(customer);
	}
	
	@Test
	public void testDelete() {
		repo.deleteById(2L);
	}
	
	@Test
	public void testM2MCreateProgrammer() {
		Programmer programmer = new Programmer();
		programmer.setName("Booya");
		programmer.setSal(10000);
		HashSet<Project> projects = new HashSet<Project>();
		Project project = new Project();
		project.setName("Hibernate");
		projects.add(project);
		
		programmer.setProjects(projects);
		
		programrepo.save(programmer);
	}
	
	@Test
	@Transactional
	public void testM2MFindProgrammer() {
		Programmer programmer = programrepo.findById(1).get();
		System.out.println(programmer);
		System.out.println(programmer.getProjects());
	}
	
	@Test
	public void testOneToOneCreateLicense() {
		License license = new License();
		license.setType("CAR");
		license.setValidFrom(new Date());
		license.setValidTo(new Date());
		
		Person person = new Person();
		person.setFirst_name("Hoochie");
		person.setLast_name("BabyMama");
		person.setAge(40);
		
		license.setPerson(person);
		
		licrepo.save(license);
	}
}



































