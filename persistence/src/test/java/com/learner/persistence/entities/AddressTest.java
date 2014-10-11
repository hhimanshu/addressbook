package com.learner.persistence.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddressTest extends AbstractUnitTest {
	@Test
	public void testAddAddress() {
		final Address address;
		{
			address = new Address("1 infinite loop", null, "Cupertino", "California", 95014, "USA");
			crudService.create(address);
			jpaRule.changeTransaction();
		}
		{
			final Address addressInDb = crudService.find(address.getId(), Address.class);
			assertNotNull(addressInDb);
			assertEquals(address, addressInDb);
		}
	}
}