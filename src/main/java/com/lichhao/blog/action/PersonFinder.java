package com.lichhao.blog.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lichhao.blog.model.Person;
import com.lichhao.blog.service.PersonService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Controller used to respond to user request to find a specific Person using
 * the person's employee ID.
 * 
 * @author bphillips
 * 
 */
@Component
@Scope("prototype")
public class PersonFinder extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * employee ID of the person to find.
	 */
	private Long emplid;

	/**
	 * Person found using the emplid.
	 */
	private Person person;

	/**
	 * Provides methods to get Person objects.
	 */
	@Resource
	private PersonService personService;

	@Override
	public String execute() throws Exception {

		person = personService.findbyEmplid(emplid);

		return SUCCESS;

	}

	public Long getEmplid() {
		return emplid;
	}

	public void setEmplid(Long emplid) {
		this.emplid = emplid;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
