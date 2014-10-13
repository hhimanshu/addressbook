package com.learner.persistence.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

public class AddressTest extends AbstractUnitTest {
	@Test
	public void testAddAddress() {
		final Address address;
		{
			final User user = new User("Steve", "Jobs");
			address = new Address("1 infinite loop", null, "Cupertino", "California", 95014, "USA", user);
			crudService.create(address);
			jpaRule.changeTransaction();
		}
		{
			final Address addressInDb = crudService.find(address.getId(), Address.class);
			assertNotNull(addressInDb);
			assertEquals(address, addressInDb);
		}
	}

	@Ignore("not deleting the address, some bug here")
	@Test
	public void testDeleteAddress() {
		final Address address;
		final User user;
		{
			user = new User("Steve", "Jobs");
			address = new Address("1 infinite loop", null, "Cupertino", "California", 95014, "USA", user);
			crudService.create(address);
			jpaRule.changeTransaction();
		}
		{
			final Address addressInDb = crudService.find(address.getId(), Address.class);
			assertNotNull(addressInDb);
			assertEquals(address, addressInDb);
		}

		{
			crudService.delete(address);
			jpaRule.changeTransaction();
			assertNotNull(crudService.find(user.getId(), User.class));
			assertNull(crudService.find(address.getId(), Address.class));
		}
	}
}