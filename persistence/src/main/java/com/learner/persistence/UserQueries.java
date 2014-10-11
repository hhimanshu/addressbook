package com.learner.persistence;

import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.QAddress;
import com.learner.persistence.entities.QPhone;
import com.learner.persistence.entities.QUser;
import com.learner.persistence.entities.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.List;

public class UserQueries {
	private static final QUser user = QUser.user;
	private static final QAddress address = QAddress.address;
	private static final QPhone phone = QPhone.phone;

	private final CrudService crudService;

	@Inject
	public UserQueries(@Nonnull final CrudService crudService) {
		this.crudService = crudService;
	}

	@Nullable
	public User getUserById(@Nonnull final String userId) {
		return crudService.query(user).where(user.id.eq(userId)).uniqueResult(user);
	}

	@Nonnull
	public List<User> getUserByState(@Nonnull final String state) {
		return crudService.query(user).innerJoin(user.addresses, address).where(address.state.eq(state)).list(user);
	}

	@Nonnull
	public List<User> getUserByPhoneAreaCode(final int areaCode) {
		return crudService.query(user).innerJoin(user.phones, phone).where(phone.areaCode.eq(areaCode)).list(user);
	}
}
