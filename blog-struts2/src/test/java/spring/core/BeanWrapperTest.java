package spring.core;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

public class BeanWrapperTest {

	@Test
	public void testBeanWrapper() {
		BeanWrapper company = new BeanWrapperImpl(new Company());
		// setting the company name..
		company.setPropertyValue("name", "Some Company Inc.");
		// ... can also be done like this:
		assertEquals("Some Company Inc.",
				(String) company.getPropertyValue("name"));

		PropertyValue value = new PropertyValue("name", "Other Company Inc.");
		company.setPropertyValue(value);
		assertEquals("Other Company Inc.",
				(String) company.getPropertyValue("name"));

		// ok, let's create the director and tie it to the company:
		BeanWrapper jim = new BeanWrapperImpl(new Employee());
		jim.setPropertyValue("name", "Jim Stravinsky");
		jim.setPropertyValue("salary", Float.valueOf(120.32f));
		company.setPropertyValue("managingDirector", jim.getWrappedInstance());
		// retrieving the salary of the managingDirector through the company
		Float salary = (Float) company
				.getPropertyValue("managingDirector.salary");
		assertEquals(salary, 120.32f, 0f);
	}
}
