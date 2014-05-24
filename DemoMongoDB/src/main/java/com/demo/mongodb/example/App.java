package com.demo.mongodb.example;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.demo.mongodb.example.domain.Person;

public class App {

	public static void main(String[] args) {

		// For XML
		// ApplicationContext ctx = new
		// GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

//		Person person = new Person("damaris", "calsin", 2);
////
//		 mongoOperation.save(person);
//
//		System.out.println("1. user : " + person);
//
//		Query searchUserQuery = new Query(Criteria.where("firstName").is(
//				"damaris"));
//
//		Person savedUser = mongoOperation
//				.findOne(searchUserQuery, Person.class);
//		
//		System.out.println("2. find - savedUser : " + savedUser.getFirstName());
//
//		mongoOperation.updateFirst(searchUserQuery, Update.update("secondName",
//				"Principe"), Person.class);

		
		// list of Person
		List<Person> listUser = mongoOperation.findAll(Person.class);

		System.out.println("======================" + listUser.size());
		for (Person person2 : listUser) {
			System.out.println(person2.getSecondName());
		}

	}
}
