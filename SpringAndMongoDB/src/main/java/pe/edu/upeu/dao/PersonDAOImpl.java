package pe.edu.upeu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.domain.Person;
import pe.edu.upeu.java.Students;

@Repository("personDAO")
public class PersonDAOImpl {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Person> findAllPerson() {
		return mongoTemplate.findAll(Person.class);
	}

	public Person findPersonById(String id) {
		BasicQuery query = new BasicQuery("{ _id :'" + id + "' }");
		return (Person) mongoTemplate.findOne(query, Person.class);
	}

	public void savePerson(Person person) {
		mongoTemplate.save(person);
	}

	public void removePerson(Person person) {
		mongoTemplate.remove(person);
	}
	
	public void updatePersona(Person person) {
		BasicQuery query = new BasicQuery("{ _id :'" + person.get_id() + "' }");
		Update update = new Update();
		update.set("status", person.getStatus());
		update.set("name", person.getName());
		update.set("age", person.getAge());
		update.set("telefono", person.getTelefono());
		mongoTemplate.updateFirst(query, update, Person.class);
		
	}

	public List<Students> findAllStudents() {
		return mongoTemplate.findAll(Students.class);
	}

	
}
