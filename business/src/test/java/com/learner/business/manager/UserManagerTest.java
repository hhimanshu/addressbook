package com.learner.business.manager;

import com.learner.business.presentation.UserPresentation;
import com.learner.persistence.entities.AbstractUnitTest;
import com.learner.persistence.entities.Address;
import com.learner.persistence.entities.Phone;
import com.learner.persistence.entities.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserManagerTest extends AbstractUnitTest {

    @Test
    public void testGetUserPresentationsByState() {
        final User steveJobs;
        {
            final Address address = new Address("1 infinite loop", null, "Cupertino", "California", 95014, "USA");
            crudService.create(address);
            jpaRule.changeTransaction();

            steveJobs = new User("Steve", "Jobs");
            steveJobs.addAddress(address);
            crudService.create(steveJobs);
            jpaRule.changeTransaction();
        }

        final List<UserPresentation> userPresentationsByState = new UserManager(crudService).getUsersByState("California");
        assertEquals("1 user must be present in CA", 1, userPresentationsByState.size());
        assertEquals(steveJobs.getFirstName(), userPresentationsByState.get(0).getFirstName());
        assertEquals(steveJobs.getLastName(), userPresentationsByState.get(0).getLastName());
        assertEquals(steveJobs.getAddresses().get(0).getStreetName(), userPresentationsByState.get(0).getAddressPresentations().get(0).getStreetName());
        assertEquals(steveJobs.getAddresses().get(0).getCity(), userPresentationsByState.get(0).getAddressPresentations().get(0).getCity());
        assertEquals(steveJobs.getAddresses().get(0).getState(), userPresentationsByState.get(0).getAddressPresentations().get(0).getState());
        assertEquals(steveJobs.getAddresses().get(0).getZip(), userPresentationsByState.get(0).getAddressPresentations().get(0).getZip());
        assertEquals(steveJobs.getAddresses().get(0).getCountry(), userPresentationsByState.get(0).getAddressPresentations().get(0).getCountry());
    }

    @Test
    public void testGetUserPresentationsByPhoneAreaCode() {
        final User steveJobs;
        {
            final Phone phone = new Phone(1, 650, 2345678);
            crudService.create(phone);
            jpaRule.changeTransaction();

            steveJobs = new User("Steve", "Jobs");
            steveJobs.addPhone(phone);
            crudService.create(steveJobs);
            jpaRule.changeTransaction();
        }

        final List<UserPresentation> userPresentationsByPhoneAreaCode = new UserManager(crudService).getUsersByPhoneAreaCode(650);
        assertEquals("1 user must be present in by phone area code", 1, userPresentationsByPhoneAreaCode.size());
        assertEquals(steveJobs.getFirstName(), userPresentationsByPhoneAreaCode.get(0).getFirstName());
        assertEquals(steveJobs.getLastName(), userPresentationsByPhoneAreaCode.get(0).getLastName());
        assertEquals(steveJobs.getPhones().get(0).getId(), userPresentationsByPhoneAreaCode.get(0).getPhonePresentations().get(0).getId());
    }
}