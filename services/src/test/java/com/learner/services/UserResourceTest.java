package com.learner.services;

import com.learner.business.manager.UserManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

public class UserResourceTest {
    @Mock
    private UserManager userManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsersByState() throws IOException {
        final UserResource userResource = new UserResource(userManager);
        Mockito.when(userManager.getUsersByState(any(String.class))).thenReturn(Collections.emptyList());

        final Response response = userResource.getUsersByState("California");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetUsersPhoneAreaCode() throws IOException {
        final UserResource userResource = new UserResource(userManager);
        Mockito.when(userManager.getUsersByPhoneAreaCode(any(Integer.class))).thenReturn(Collections.emptyList());

        final Response response = userResource.getUsersByPhoneAreaCode(408);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}