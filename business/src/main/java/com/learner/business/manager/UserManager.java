package com.learner.business.manager;

import com.learner.persistence.UserQueries;
import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.User;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;

public class UserManager {
	private final UserQueries userQueries;

	@Inject
	public UserManager(@Nonnull final CrudService crudService) {
		userQueries = new UserQueries(crudService);
	}

	@Nonnull
	public List<User> getUsersByState(@Nonnull final String state) {
		return userQueries.getUserByState(state);
	}

	@Nonnull
	public List<User> getUsersByPhoneAreaCode(final int phoneAreaCode) {
		return userQueries.getUserByPhoneAreaCode(phoneAreaCode);
	}
}
