package com.learner.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserTest extends AbstractUnitTest {
	@Test
	public void addUser() {
		final User user;
		{
			user = new User("Steve", "Jobs");
			crudService.create(user);
			jpaRule.changeTransaction();
		}
		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertEquals(user, userInDb);
		}
	}
}