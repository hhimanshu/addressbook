package com.learner.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UserTest extends AbstractUnitTest {
	@Test
	public void testAddUser() {
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

	@Test
	public void testAddUserWithNickName() {
		final User user;
		{
			user = new User("Steve", "Jobs");
			user.setNickName("steve");
			crudService.create(user);
			jpaRule.changeTransaction();
		}
		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertNotNull("user nickname must not be null", userInDb.getNickName());
			assertEquals(user, userInDb);
		}
	}

	@Test
	public void testAddUserWithEmail() {
		final User user;
		{
			user = new User("Steve", "Jobs");
			user.addEmail("steve@apple.com");
			user.addEmail("steve@gmail.com");
			crudService.create(user);
			jpaRule.changeTransaction();
		}
		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertEquals("Two emails not present", 2, userInDb.getEmails().size());
			assertEquals(user, userInDb);
		}
	}

	@Test
	public void testAddUserWithPhone() {
		final User user;
		{
			user = new User("Steve", "Jobs");
			Phone phone1 = new Phone(1, 555, 2345142);
			Phone phone2 = new Phone(1, 555, 2348769);

			user.addPhone(phone1);
			user.addPhone(phone2);
			crudService.create(phone1);
			crudService.create(phone2);
			jpaRule.changeTransaction();

			crudService.create(user);
			jpaRule.changeTransaction();
		}
		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertEquals("Two phones not present", 2, userInDb.getPhones().size());
			assertEquals(user, userInDb);
		}
	}

	@Test
	public void testAddUserWithAddress() {
		final User user;
		{
			user = new User("Steve", "Jobs");
			Address address1 = new Address("1 Infinite Loop", null, "Cupertino", "California", 94105, "USA");
			Address address2 = new Address("2855 Stevens Creek Blvd", "#1025", "Santa Clara", "California", 95050, "USA");
			crudService.create(address1);
			crudService.create(address2);
			jpaRule.changeTransaction();

			user.addAddress(address1);
			user.addAddress(address2);
			crudService.create(user);
			jpaRule.changeTransaction();
		}
		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertEquals("Two phones not present", 2, userInDb.getAddresses().size());
			assertEquals(user, userInDb);
		}
	}
}