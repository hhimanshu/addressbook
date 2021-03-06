package com.learner.business.presentation;

import com.learner.persistence.entities.Phone;
import com.learner.persistence.entities.PhoneNumberType;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class PhonePresentation {
    private String id;
    private String phone;
    private PhoneNumberType phoneNumberType;

    public PhonePresentation(@Nonnull final Phone phone) {
        id = phone.getId();
        phoneNumberType = phone.getPhoneNumberType();
        final StringBuilder phoneFormatBuilder = new StringBuilder();

        // build country code
        phoneFormatBuilder.append("(+");
        phoneFormatBuilder.append(phone.getCountryCode());
        phoneFormatBuilder.append(") ");

        // build area code
        phoneFormatBuilder.append(phone.getAreaCode()).append("-");

        // build first 3 letters of Number
        // todo (harit): better way to transform, this can throw errors
        final String numberAsString = String.valueOf(phone.getNumber());
        phoneFormatBuilder.append(numberAsString.substring(0, 3)).append("-");
        phoneFormatBuilder.append(numberAsString.substring(3, numberAsString.length()));

        this.phone = phoneFormatBuilder.toString();
    }

    @Nonnull
    public static List<PhonePresentation> getPhonePresentations(@Nonnull final List<Phone> phones) {
        return phones.stream().map(PhonePresentation::new).collect(Collectors.toList());
    }

    @Nonnull
    public String getId() {
        return id;
    }

    @Nonnull
    public String getPhone() {
        return phone;
    }

    @Nonnull
    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    @Override
    public String toString() {
        return "PhonePresentation{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", phoneNumberType=" + phoneNumberType +
                '}';
    }
}
