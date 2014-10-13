package com.learner.persistence.entities;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private User user;

	public Address(@Nonnull final String streetName, @Nullable final String additionalStreetName,
	               @Nonnull final String city, @Nonnull final String state, int zip,
	               @Nonnull final String country, final User user) {
		this(streetName, additionalStreetName, city, state, zip, country, AddressType.HOME, user);
	}

	public Address(@Nonnull final String streetName, @Nullable final String additionalStreetName,
	               @Nonnull final String city, @Nonnull final String state, int zip,
	               @Nonnull final String country, @Nonnull final AddressType addressType, final User user) {
		id = UUID.randomUUID().toString();
		this.streetName = streetName;
		this.additionalStreetName = additionalStreetName;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.addressType = addressType;
		this.user = user;
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

	@Nonnull
	public User getUser() {
		return user;
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
