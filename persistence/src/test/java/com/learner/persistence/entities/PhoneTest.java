package com.learner.persistence.entities;

import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.harness.JpaRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneTest {
	@Rule
	public JpaRule jpaRule = new JpaRule("unit-testing-pu");
	private CrudService crudService;

	@Before
	public void setUp() {
		crudService = jpaRule.createCrudService(CrudService.class);
	}

	@Test
	public void testAddPhone() {
		final Phone phone;
		{
			phone = new Phone(1, 650, 2345678);
			crudService.create(phone);
			jpaRule.changeTransaction();
		}
		{
			final Phone phoneInDb = crudService.find(phone.getId(), Phone.class);
			assertNotNull(phoneInDb);
			assertEquals(phone, phoneInDb);
		}
	}
}
