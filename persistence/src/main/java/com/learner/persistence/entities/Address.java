package com.learner.persistence.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {
	@Id
	private String id;
	private String streetName;
	private String additionalStreetName;
	private String city;
	private String state;
	private int zip;
	private String country;
	private AddressType addressType;

	public Address(@Nonnull final String streetName, @Nullable final String additionalStreetName,
	               @Nonnull final String city, @Nonnull final String state, int zip,
	               @Nonnull final String country) {
		this(streetName, additionalStreetName, city, state, zip, country, AddressType.HOME);
	}

	public Address(@Nonnull final String streetName, @Nullable final String additionalStreetName,
	               @Nonnull final String city, @Nonnull final String state, int zip,
	               @Nonnull final String country, @Nonnull final AddressType addressType) {
		id = UUID.randomUUID().toString();
		this.streetName = streetName;
		this.additionalStreetName = additionalStreetName;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.addressType = addressType;
	}

	public Address() {
		// required by JPA
	}

	@Nonnull
	public String getId() {
		return id;
	}

	@Nonnull
	public String getStreetName() {
		return streetName;
	}

	@Nullable
	public String getAdditionalStreetName() {
		return additionalStreetName;
	}

	@Nonnull
	public String getCity() {
		return city;
	}

	@Nonnull
	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	@Nonnull
	public String getCountry() {
		return country;
	}

	@Nonnull
	public AddressType getAddressType() {
		return addressType;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id='" + id + '\'' +
				", streetName='" + streetName + '\'' +
				", additionalStreetName='" + additionalStreetName + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", zip=" + zip +
				", country='" + country + '\'' +
				", addressType=" + addressType +
				'}';
	}
}
