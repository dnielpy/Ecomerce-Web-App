package com.example.demo.User;

public class UserDTO {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String country;
    private final String city;
    private final String address;
    private final String tel;
    private final String mobile;

    public UserDTO(String email, String firstName, String lastName, String country, String city, String address, String tel, String mobile) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.address = address;
        this.tel = tel;
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", country='" + country + '\'' + ", city='" + city + '\'' + ", address='" + address + '\'' + ", tel='" + tel + '\'' + ", mobile='" + mobile + '\'' + '\'' + '}';
    }
}