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
public class TestHello {

    @Test
    public void testGetHello() throws IOException {
        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target("http://localhost:9090/addressBook/rest/hello");
        final Response response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final JsonNode responsePayload = new ObjectMapper().readTree(response.readEntity(String.class));
        assertTrue(responsePayload.has("hello"));
    }
}