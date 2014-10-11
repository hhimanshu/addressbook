package com.learner.business.presentation;

import com.learner.persistence.entities.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserPresentation {
    private String id;
    private String firstName;
    private String lastName;
    private String nickName;
    private final List<String> emails = new ArrayList<>();
    private final List<PhonePresentation> phonePresentations = new ArrayList<>();
    private final List<AddressPresentation> addressPresentations = new ArrayList<>();

    public UserPresentation(@Nonnull final User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        nickName = user.getNickName();
        emails.addAll(user.getEmails());
        phonePresentations.addAll(PhonePresentation.getPhonePresentations(user.getPhones()));
        addressPresentations.addAll(AddressPresentation.getAddressPresentations(user.getAddresses()));
    }

    @Nonnull
    public static List<UserPresentation> getUserPresentations(@Nonnull final List<User> users) {
        return users.stream().map(UserPresentation::new).collect(Collectors.toList());
    }

    @Nonnull
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

    @Nullable
    public String getNickName() {
        return nickName;
    }

    @Nonnull
    public List<String> getEmails() {
        return emails;
    }

    @Nonnull
    public List<PhonePresentation> getPhonePresentations() {
        return phonePresentations;
    }

    @Nonnull
    public List<AddressPresentation> getAddressPresentations() {
        return addressPresentations;
    }

    @Override
    public String toString() {
        return "UserPresentation{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", emails=" + emails +
                ", phonePresentations=" + phonePresentations +
                ", addressPresentations=" + addressPresentations +
                '}';
    }
}
