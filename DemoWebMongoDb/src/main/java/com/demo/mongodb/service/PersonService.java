package com.demo.mongodb.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.demo.mongodb.domain.MeterEnergyMonthlyUsage;
import com.demo.mongodb.domain.Person;

@Repository
public class PersonService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "person";
	public static final String COLLECTION_NAME_METER = "MeterEnergyMonthlyUsage";

	public void addPerson(Person person) {
		if (!mongoTemplate.collectionExists(Person.class)) {
			mongoTemplate.createCollection(Person.class);
		}
		person.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(person, COLLECTION_NAME);
	}

	public List<Person> listPerson() {
		return mongoTemplate.findAll(Person.class, COLLECTION_NAME);
	}

	public void deletePerson(Person person) {
		mongoTemplate.remove(person, COLLECTION_NAME);
	}

	public void updatePerson(Person person) {
		mongoTemplate.insert(person, COLLECTION_NAME);
	}

//	public void updateMeter(MeterEnergyMonthlyUsage meter) {
//		mongoTemplate.insert(meter, COLLECTION_NAME_METER);
//	}

	public void updateMeter(MeterEnergyMonthlyUsage meterEnergyMonthlyUsage) {
		// TODO Auto-generated method stub
		mongoTemplate.insert(meterEnergyMonthlyUsage, COLLECTION_NAME_METER);
	}

	public List<MeterEnergyMonthlyUsage> listMeterEnergyMonthlyUsage() {
		return mongoTemplate.findAll(MeterEnergyMonthlyUsage.class, COLLECTION_NAME_METER);
	}

}
