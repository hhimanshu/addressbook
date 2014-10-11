package com.learner.persistence;

import com.learner.persistence.entities.AbstractUnitTest;
import com.learner.persistence.entities.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserQueriesTest extends AbstractUnitTest {

	@Test
	public void testGetUserById() throws Exception {
		final User user;
		{
			user = crudService.create(new User("Steve", "Jobs"));
			jpaRule.changeTransaction();
		}
		final User userByQuery = new UserQueries(crudService).getUserById(user.getId());
		assertNotNull(userByQuery);
		assertEquals(user, userByQuery);
	}
}