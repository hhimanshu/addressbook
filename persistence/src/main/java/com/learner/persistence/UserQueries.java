package com.learner.persistence;

import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.QUser;
import com.learner.persistence.entities.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class UserQueries {
	private static final QUser user = QUser.user;
	private final CrudService crudService;

	@Inject
	public UserQueries(@Nonnull final CrudService crudService) {
		this.crudService = crudService;
	}

	@Nullable
	public User getUserById(@Nonnull final String userId) {
		return crudService.query(user).where(user.id.eq(userId)).uniqueResult(user);
	}
}
