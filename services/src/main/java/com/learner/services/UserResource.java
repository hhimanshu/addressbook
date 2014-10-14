package com.learner.services;

import com.learner.business.manager.UserManager;
import com.learner.business.presentation.UserPresentation;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UserResource {
    private UserManager userManager;

    @SuppressWarnings("UnusedDeclaration")
    public UserResource() {
    }

    @Inject
    public UserResource(@Nonnull final UserManager userManager) {
        this.userManager = userManager;
    }

    @GET
    @Path("state/{state}")
    public Response getUsersByState(@Nonnull final @PathParam("state") String state) {
        final List<UserPresentation> userPresentations = userManager.getUsersByState(state);
        return Response.ok(userPresentations).build();
    }

    @GET
    @Path("areaCode/{phoneAreaCode}")
    public Response getUsersByPhoneAreaCode(final @PathParam("phoneAreaCode") int phoneAreaCode) {
        final List<UserPresentation> userPresentations = userManager.getUsersByPhoneAreaCode(phoneAreaCode);
        return Response.ok(userPresentations).build();
    }
}
