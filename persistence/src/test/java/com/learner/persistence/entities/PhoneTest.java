package com.learner.persistence.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class PhoneTest extends AbstractUnitTest {

	@Test
	public void testAddPhone() {
		final Phone phone;
		{
			final User user = new User("Steve", "Jobs");
			phone = new Phone(1, 650, 2345678, user);
			crudService.create(phone);
			jpaRule.changeTransaction();
		}
		{
			final Phone phoneInDb = crudService.find(phone.getId(), Phone.class);
			assertNotNull(phoneInDb);
			assertEquals(phone, phoneInDb);
		}
	}

	@Test
	public void testDeletePhone() {
		final Phone phone;
		final User user;
		{
			user = new User("Steve", "Jobs");
			phone = new Phone(1, 650, 2345678, user);
			crudService.create(phone);
			jpaRule.changeTransaction();
		}
		{
			final Phone phoneInDb = crudService.find(phone.getId(), Phone.class);
			assertNotNull(phoneInDb);
			assertEquals(phone, phoneInDb);
		}

		// deleting phone doesn't deletes user
		{
			crudService.delete(phone);
			jpaRule.changeTransaction();
			assertNull(crudService.find(phone.getId(), Phone.class));
			assertNotNull(crudService.find(user.getId(), User.class));
		}
	}
}
