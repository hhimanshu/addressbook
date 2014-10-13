package com.learner.persistence.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

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
			final Phone phone1 = new Phone(1, 555, 2345142, user);
			final Phone phone2 = new Phone(1, 555, 2348769, user);

			crudService.create(phone1);
			crudService.create(phone2);

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
			final Address address1 = new Address("1 Infinite Loop", null, "Cupertino", "California", 94105, "USA", user);
			final Address address2 = new Address("2855 Stevens Creek Blvd", "#1025", "Santa Clara", "California", 95050, "USA", user);

			crudService.create(address1);
			crudService.create(address2);
			jpaRule.changeTransaction();
		}
		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertEquals("Two address not present", 2, userInDb.getAddresses().size());
			assertEquals(user, userInDb);
		}
	}

	@Test
	public void testDeleteUser() {
		final User user = new User("Steve", "Jobs");
		final Address address1;
		final Address address2;
		{
			address1 = new Address("1 Infinite Loop", null, "Cupertino", "California", 94105, "USA", user);
			address2 = new Address("2855 Stevens Creek Blvd", "#1025", "Santa Clara", "California", 95050, "USA", user);

			crudService.create(address1);
			crudService.create(address2);
			jpaRule.changeTransaction();
		}

		final Phone phone1;
		final Phone phone2;
		{
			phone1 = new Phone(1, 555, 2345142, user);
			phone2 = new Phone(1, 555, 2348769, user);

			crudService.create(phone1);
			crudService.create(phone2);
			jpaRule.changeTransaction();
		}

		{
			final User userInDb = crudService.find(user.getId(), User.class);
			assertNotNull(userInDb);
			assertEquals("Two address not present", 2, userInDb.getAddresses().size());
			assertEquals("Two phones not present", 2, userInDb.getPhones().size());
			assertEquals(user, userInDb);

			crudService.delete(userInDb);
			jpaRule.changeTransaction();
		}

		// deleting user cascades delete to address and phone
		{
			assertNull(crudService.find(user.getId(), User.class));
			assertNull(crudService.find(phone1.getId(), Phone.class));
			assertNull(crudService.find(phone2.getId(), Phone.class));
			assertNull(crudService.find(address1.getId(), Address.class));
			assertNull(crudService.find(address2.getId(), Address.class));
		}
	}
}