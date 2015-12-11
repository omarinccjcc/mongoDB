package com.demo.mongodb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.mongodb.domain.MeterEnergyMonthlyUsage;
import com.demo.mongodb.domain.Person;
import com.demo.mongodb.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String getPersonList(ModelMap model) {
		model.addAttribute("personList", personService.listPerson());

		System.out.println(new Date());
		List<MeterEnergyMonthlyUsage> list = personService.listMeterEnergyMonthlyUsage();
		System.out.println(new Date());
		System.out.println("list : " + list.size());
		model.addAttribute("meterList", list);
		return "person";
	}

	@RequestMapping(value = "/person/save", method = RequestMethod.POST)
	public View createPerson(@ModelAttribute Person person, ModelMap model) {
		if (StringUtils.hasText(person.getId())) {
			personService.updatePerson(person);
		} else {
			personService.addPerson(person);
		}
		System.out.println(new Date());
		// save();
		System.out.println(new Date());
		return new RedirectView("/DemoWebMongoDb/person");
	}

	@RequestMapping(value = "/person/delete", method = RequestMethod.GET)
	public View deletePerson(@ModelAttribute Person person, ModelMap model) {
		System.out.println("person: " + person);
		personService.deletePerson(person);
		return new RedirectView("/DemoWebMongoDb/person");
	}

	private void save() {

		MeterEnergyMonthlyUsage meterEnergyMonthlyUsage = null;
		for (int i = 0; i < 100; i++) {

			meterEnergyMonthlyUsage = new MeterEnergyMonthlyUsage("439", "4929", "21360.000000000000000",
					"3907.0000000000000", "4085.0000000000000");
			personService.updateMeter(meterEnergyMonthlyUsage);
		}
	}

}
