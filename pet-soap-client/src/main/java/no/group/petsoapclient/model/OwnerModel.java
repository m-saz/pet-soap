package no.group.petsoapclient.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class OwnerModel {

    private BigInteger id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private String address;
    private List<PetModel> pets;
}
