package com.learner.integration;

import com.learner.persistence.harness.Integration;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Category(Integration.class)
public class TestUser {

    @Test
    public void testGetUsersByState() throws IOException {
        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target("http://localhost:9090/addressBook/rest/users/state/California");
        final Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final JsonNode responsePayload = new ObjectMapper().readTree(response.readEntity(String.class));

        assertEquals(1, responsePayload.size());
        assertTrue(responsePayload.get(0).has("id"));
        assertTrue(responsePayload.get(0).has("firstName"));
        assertTrue(responsePayload.get(0).has("lastName"));
        assertEquals("The user must be Steve Jobs", "Steve", responsePayload.get(0).get("firstName").asText());
        assertEquals("The user must be Steve Jobs", "Jobs", responsePayload.get(0).get("lastName").asText());

        assertTrue(responsePayload.get(0).has("nickName"));
        assertTrue(responsePayload.get(0).has("email"));
        assertTrue(responsePayload.get(0).has("phonePresentations"));
        assertEquals("2 Phones are present", 2, responsePayload.get(0).get("phonePresentations").size());
        assertTrue(responsePayload.get(0).has("addressPresentations"));
        assertEquals("1 address is present", 1, responsePayload.get(0).get("addressPresentations").size());
    }

    @Test
    public void testGetUsersByPhoneAreaCode() throws IOException {
        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target("http://localhost:9090/addressBook/rest/users/areaCode/202");
        final Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final JsonNode responsePayload = new ObjectMapper().readTree(response.readEntity(String.class));

        assertEquals(1, responsePayload.size());
        assertTrue(responsePayload.get(0).has("id"));
        assertTrue(responsePayload.get(0).has("firstName"));
        assertEquals("The user must be Bill Gates", "Bill", responsePayload.get(0).get("firstName").asText());
        assertEquals("The user must be Bill Gates", "Gates", responsePayload.get(0).get("lastName").asText());
        assertTrue(responsePayload.get(0).has("lastName"));
        assertTrue(responsePayload.get(0).has("nickName"));
        assertTrue(responsePayload.get(0).has("email"));
        assertTrue(responsePayload.get(0).has("phonePresentations"));
        assertEquals("2 Phones are present", 1, responsePayload.get(0).get("phonePresentations").size());
        assertTrue(responsePayload.get(0).has("addressPresentations"));
        assertEquals("1 address is present", 1, responsePayload.get(0).get("addressPresentations").size());
    }
}
