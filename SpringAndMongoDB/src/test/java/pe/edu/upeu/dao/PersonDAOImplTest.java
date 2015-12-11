package pe.edu.upeu.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upeu.domain.Person;

public class PersonDAOImplTest extends AbstractUnitTest {
	
	@Autowired
	PersonDAOImpl personDAO;

	@Test
	public void findAllPerson() {
		System.out.println("Testing....");
		List<Person> list = personDAO.findAllPerson();
		for (Person person : list) {
			System.out.println(person.getName());
		}
	}

	@Test
	public void savePerson() {
		System.out.println("savePerson....");
		Person person = new Person();
		person.setName("Elena");
		person.setLastName("Mamani Mamani");
		person.setStatus("Activo");
		person.setAge("30");
		person.setDni("2111111");
		person.setTelefono("995590753");
		personDAO.savePerson(person);
		findAllPerson();
	}

	@Test
	public void updatePersona() {
		System.out.println("updatePersona....");
		Person person = new Person();
		person.set_id("566072553be23dbf009e2414");
		person.setName("Maria");
		person.setLastName("Diaz Diaz");
		person.setStatus("Activo");
		person.setAge("30");
		person.setDni("42114381");
		person.setTelefono("051321692");

		personDAO.updatePersona(person);
		findAllPerson();
	}

	@Test
	public void findPersonById() {
		System.out.println("findPersonById....");
		Person person = personDAO.findPersonById("566072553be23dbf009e2414");
		System.out.println(person.getName());
		 
	}

	@Test
	public void removePerson() {
		System.out.println("removePerson....");

		Person person = personDAO.findPersonById("566073663be2b133b0bda7c6");
		
		personDAO.removePerson(person);
		
		person = personDAO.findPersonById("566073663be2b133b0bda7c6");
		System.out.println(person);
		 
	}

}
