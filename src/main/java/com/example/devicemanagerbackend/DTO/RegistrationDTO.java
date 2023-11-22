package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.enums.UserType;

public class RegistrationDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private UserType userType;

    public RegistrationDTO() {
        super();
    }

    public RegistrationDTO(String firstName, String middleName, String lastName, String email, String password, UserType userType) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String toString(){
        return "Email: " + this.email + " Password: " + this.password;
    }

}
