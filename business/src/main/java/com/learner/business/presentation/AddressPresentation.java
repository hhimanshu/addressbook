package com.learner.business.presentation;

import com.learner.persistence.entities.Address;
import com.learner.persistence.entities.AddressType;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AddressPresentation {
    private String id;
    private String streetName;
    private String additionalStreetName;
    private String city;
    private String state;
    private int zip;
    private String country;
    private AddressType addressType;

    public AddressPresentation(@Nonnull final Address address) {
        id = address.getId();
        streetName = address.getStreetName();
        additionalStreetName = address.getAdditionalStreetName();
        city = address.getCity();
        state = address.getState();
        zip = address.getZip();
        country = address.getCountry();
        addressType = address.getAddressType();
    }

    @Override
    public String toString() {
        return "AddressPresentation{" +
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

    @Nonnull
    public static Collection<? extends AddressPresentation> getAddressPresentations(@Nonnull final List<Address> addresses) {
        return addresses.stream().map(AddressPresentation::new).collect(Collectors.toList());
    }
}
