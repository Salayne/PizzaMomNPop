package com.example.momnpop;

public class UserInfo {
    private final String firstName;
    private final String lastName;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    UserInfo(String firstName, String lastName, String street,String city, String state, String zip){
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
