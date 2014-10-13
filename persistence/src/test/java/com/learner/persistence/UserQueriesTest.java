package com.learner.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.learner.persistence.entities.AbstractUnitTest;
import com.learner.persistence.entities.Address;
import com.learner.persistence.entities.Phone;
import com.learner.persistence.entities.User;

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

	@Test
	public void testGetUserByState() throws Exception {
		final User billGates;
		{
			billGates = new User("Bill", "Gates");
			final Address address1 = new Address("One Microsoft Way", null, "Redmond", "Washington", 98052, "USA", billGates);
			crudService.create(address1);
			jpaRule.changeTransaction();
		}

		final User steveJobs;
		{
			steveJobs = new User("Steve", "Jobs");
			final Address address2 = new Address("1 infinite loop", null, "Cupertino", "California", 95014, "USA", steveJobs);
			crudService.create(address2);
			jpaRule.changeTransaction();
		}

		final User elonMusk;
		{
			elonMusk = new User("Elon", "Musk");
			final Address address3 = new Address("45500 Fremont Blvd", null, "Fremont", "California", 94538, "USA", elonMusk);
			crudService.create(address3);
			jpaRule.changeTransaction();
		}

		final UserQueries userQueries = new UserQueries(crudService);
		{
			final List<User> californiaUsers = userQueries.getUserByState("California");
			assertNotNull(californiaUsers);
			assertEquals("Two users must be found in CA", 2, californiaUsers.size());
			assertEquals(steveJobs, californiaUsers.get(0));
			assertEquals(elonMusk, californiaUsers.get(1));
		}
		{
			final List<User> washingtonUsers = userQueries.getUserByState("Washington");
			assertNotNull(washingtonUsers);
			assertEquals("1 user must be found in WA", 1, washingtonUsers.size());
			assertEquals(billGates, washingtonUsers.get(0));
		}
	}

	@Test
	public void testGetUsersByPhoneAreaCode() {
		final User billGates;
		{
			billGates = new User("Bill", "Gates");
			final Phone phone = new Phone(1, 425, 8828080, billGates);
			crudService.create(phone);
			jpaRule.changeTransaction();
		}
		final User steveJobs;
		{
			steveJobs = new User("Steve", "Jobs");
			final Phone phone = new Phone(1, 408, 9961010, steveJobs);
			crudService.create(phone);
			jpaRule.changeTransaction();
		}

		final UserQueries userQueries = new UserQueries(crudService);
		{
			final List<User> californiaUsers = userQueries.getUserByPhoneAreaCode(408);
			assertEquals("1 user must be found in CA", 1, californiaUsers.size());
			assertEquals("CA user is Steve Jobs", steveJobs, californiaUsers.get(0));
		}
		{
			final List<User> washingtonUsers = userQueries.getUserByPhoneAreaCode(425);
			assertEquals("1 user must be found in CA", 1, washingtonUsers.size());
			assertEquals("CA user is Bill Gates", billGates, washingtonUsers.get(0));
		}
	}
}