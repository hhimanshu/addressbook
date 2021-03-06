package com.learner.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("hello")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {
        return Response.ok(Collections.singletonMap("hello", "world")).build();
    }
}
