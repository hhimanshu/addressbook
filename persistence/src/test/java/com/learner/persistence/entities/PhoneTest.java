package com.learner.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PhoneTest extends AbstractUnitTest {

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
