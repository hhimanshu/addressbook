package com.learner.integration;

import com.learner.persistence.harness.Integration;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Category(Integration.class)
public class TestUser {

    @Ignore("enable after test data setup")
    @Test
    public void testGetUsersByState() throws IOException {
        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target("http://localhost:9090/addressBook/rest/users/state/california");
        final Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final JsonNode responsePayload = new ObjectMapper().readTree(response.readEntity(String.class));
        System.out.println(responsePayload.toString());
        assertEquals(5, responsePayload.size());
    }
}
