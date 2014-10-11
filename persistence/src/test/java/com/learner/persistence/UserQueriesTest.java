package com.learner.persistence;

import com.learner.persistence.entities.AbstractUnitTest;
import com.learner.persistence.entities.Address;
import com.learner.persistence.entities.User;
import org.junit.Test;

import java.util.List;

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

	@Test
	public void testGetUserByState() throws Exception {
		final User billGates;
		{
			final Address billGatesAddress;
			{
				billGatesAddress = new Address("One Microsoft Way", null, "Redmond", "Washington", 98052, "USA");
				crudService.create(billGatesAddress);
				jpaRule.changeTransaction();
			}
			{
				billGates = crudService.create(new User("Steve", "Jobs"));
				billGates.addAddress(billGatesAddress);
				jpaRule.changeTransaction();
			}
		}

		final User steveJobs;
		{
			final Address steveJobsAddress;
			{
				steveJobsAddress = new Address("1 infinite loop", null, "Cupertino", "California", 95014, "USA");
				crudService.create(steveJobsAddress);
				jpaRule.changeTransaction();
			}
			{
				steveJobs = crudService.create(new User("Steve", "Jobs"));
				steveJobs.addAddress(steveJobsAddress);
				jpaRule.changeTransaction();
			}
		}

		final User elonMusk;
		{
			final Address elonMuskAddress;
			{
				elonMuskAddress = new Address("45500 Fremont Blvd", null, "Fremont", "California", 94538, "USA");
				crudService.create(elonMuskAddress);
				jpaRule.changeTransaction();
			}
			{
				elonMusk = crudService.create(new User("Elon", "Musk"));
				elonMusk.addAddress(elonMuskAddress);
				jpaRule.changeTransaction();
			}

		}

		{
			final List<User> californiaUsers = new UserQueries(crudService).getUserByState("California");
			assertNotNull(californiaUsers);
			assertEquals("Two users must be found in CA", 2, californiaUsers.size());
			assertEquals(steveJobs, californiaUsers.get(0));
			assertEquals(elonMusk, californiaUsers.get(1));
		}
		{
			final List<User> washingtonUsers = new UserQueries(crudService).getUserByState("Washington");
			assertNotNull(washingtonUsers);
			assertEquals("1 user must be found in WA", 1, washingtonUsers.size());
			assertEquals(billGates, washingtonUsers.get(0));
		}
	}
}