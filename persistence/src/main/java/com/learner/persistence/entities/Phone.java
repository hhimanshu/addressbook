package com.learner.persistence.entities;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Phone {
	@Id
	private String id;
	private int countryCode;
	private int areaCode;
	private int number;
	private PhoneNumberType phoneNumberType;

	public Phone(final int countryCode, final int areaCode, final int number) {
		id = UUID.randomUUID().toString();
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
		phoneNumberType = PhoneNumberType.HOME;
	}

	public Phone(final int countryCode, final int areaCode, final int number,
	             @Nonnull final PhoneNumberType phoneNumberType) {
		id = UUID.randomUUID().toString();
		this.countryCode = countryCode;
		this.areaCode = areaCode;
		this.number = number;
		this.phoneNumberType = phoneNumberType;
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
