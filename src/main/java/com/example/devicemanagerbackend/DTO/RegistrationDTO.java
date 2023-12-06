package com.example.devicemanagerbackend.DTO;

import com.example.devicemanagerbackend.enums.UserRole;

public class RegistrationDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private UserRole userRole;

    public RegistrationDTO() {
        super();
    }

    public RegistrationDTO(String firstName, String middleName, String lastName, String email, String password, UserRole userRole) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String toString(){
        return "Email: " + this.email + " Password: " + this.password;
    }

}
