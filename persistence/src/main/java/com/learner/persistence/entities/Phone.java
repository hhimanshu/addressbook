package com.learner.persistence.entities;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.UUID;

@Entity
public class Phone {
	@Id
	private String id;
	private int countryCode;
	private int areaCode;
	private int number;
    @Enumerated(EnumType.STRING)
    private PhoneNumberType phoneNumberType;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private User user;

	public Phone(final int countryCode, final int areaCode, final int number, @Nonnull final User user) {
		this(countryCode, areaCode, number, PhoneNumberType.HOME, user);
	}

	public Phone(final int countryCode, final int areaCode, final int number,
	             @Nonnull final PhoneNumberType phoneNumberType, @Nonnull final User user) {
		id = UUID.randomUUID().toString();
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
		this.phoneNumberType = phoneNumberType;
		this.user = user;
	}

	protected Phone() {
		// required by JPA
	}

	@Nonnull
	public String getId() {
		return id;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public int getNumber() {
		return number;
	}

	@Nonnull
	public PhoneNumberType getPhoneNumberType() {
		return phoneNumberType;
	}

	@Nonnull
	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Phone{" +
				"id='" + id + '\'' +
				", countryCode=" + countryCode +
				", areaCode=" + areaCode +
				", number=" + number +
				", phoneNumberType=" + phoneNumberType +
				'}';
	}
}
