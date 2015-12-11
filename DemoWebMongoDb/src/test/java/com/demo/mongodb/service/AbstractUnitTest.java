package com.demo.mongodb.service;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.demo.mongodb.domain.Person;

public class AbstractUnitTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new ClassPathResource("dispatcher-servlet.xml").getPath());
		PersonService personService = context.getBean(PersonService.class);
		List<Person> list = personService.listPerson();
		System.out.println(list);
		
	}
}
