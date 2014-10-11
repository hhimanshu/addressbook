package com.learner.business.manager;

import com.learner.business.presentation.UserPresentation;
import com.learner.persistence.UserQueries;
import com.learner.persistence.configuration.CrudService;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;

public class UserManager {
    private final CrudService crudService;

	@Inject
	public UserManager(@Nonnull final CrudService crudService) {
        this.crudService = crudService;
    }

	@Nonnull
	public List<UserPresentation> getUsersByState(@Nonnull final String state) {
		return UserPresentation.getUserPresentations(new UserQueries(crudService).getUserByState(state));
	}

	@Nonnull
	public List<UserPresentation> getUsersByPhoneAreaCode(final int phoneAreaCode) {
		return UserPresentation.getUserPresentations(new UserQueries(crudService).getUserByPhoneAreaCode(phoneAreaCode));
	}
}
