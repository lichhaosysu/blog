package com.lichhao.blog.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lichhao.blog.model.Person;
import com.lichhao.blog.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Acts as the controller for handling user requests to delete a Person object.
 * 
 * @author brucephillips
 * 
 */
@Component
@Scope("prototype")
public class PersonDeleter extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private PersonService personService;

	private Person person;

	private Long emplid;

	/**
	 * Use emplid provided in the user's request to find the Person object and
	 * then delete that Person's record.
	 */
	public String execute() {

		person = personService.findbyEmplid(emplid);

		personService.delete(person);

		return SUCCESS;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getEmplid() {
		return emplid;
	}

	public void setEmplid(Long emplid) {
		this.emplid = emplid;
	}

}
