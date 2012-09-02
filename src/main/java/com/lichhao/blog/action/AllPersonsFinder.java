package com.lichhao.blog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lichhao.blog.model.Person;
import com.lichhao.blog.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Controller used to respond to user request to find all employees.
 * 
 * @author bphillips
 * 
 */
@Component
@Scope("prototype")
public class AllPersonsFinder extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * Collection of all persons.
	 */
	private List<Person> persons;

	/**
	 * Provides methods to get Person objects.
	 */
	@Resource
	private PersonService personService;

	@Override
	public String execute() throws Exception {

		persons = personService.findAllEmployees();

		return SUCCESS;

	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
