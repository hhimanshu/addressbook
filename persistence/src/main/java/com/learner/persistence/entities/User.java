package com.learner.persistence.entities;

import javax.annotation.Nonnull;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String nickName;
	@ElementCollection
	private final List<String> emails = new ArrayList<>();
	@OneToMany
	private final List<Address> addresses = new ArrayList<>();
	@OneToMany
	private final List<Phone> phones = new ArrayList<>();

	public User(@Nonnull final String firstName, @Nonnull final String lastName) {
		id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User() {
		// required by JPA
	}

	public String getId() {
		return id;
	}

	@Nonnull
	public String getFirstName() {
		return firstName;
	}

	@Nonnull
	public String getLastName() {
		return lastName;
	}

	@Nonnull
	public String getNickName() {
		return nickName;
	}

	public void setNickName(@Nonnull final String nickName) {
		this.nickName = nickName;
	}

	@Nonnull
	public List<String> getEmails() {
		return Collections.unmodifiableList(emails);
	}

	@Nonnull
	public List<Address> getAddresses() {
		return Collections.unmodifiableList(addresses);
	}

	@Nonnull
	public List<Phone> getPhones() {
		return Collections.unmodifiableList(phones);
	}

	public void addAddress(@Nonnull final Address address) {
		addresses.add(address);
	}

	public void addPhone(@Nonnull final Phone phone) {
		phones.add(phone);
	}

	public void addEmail(@Nonnull final String email) {
		emails.add(email);
	}

	// likewise remove address, phones, emails could be added
}
