package com.example.access_pc.dao;

/**
 * Created by Access_pc on 16/05/2018.
 */

public class RegisterCustomer {
    String logusername;
    String logpassword;
    String confirmPass;
    String firstName;
    String lastName;
    String address;

    public String getLogusername() {
        return logusername;
    }

    public void setLogusername(String logusername) {
        this.logusername = logusername;
    }

    public String getLogpassword() {
        return logpassword;
    }

    public void setLogpassword(String logpassword) {
        this.logpassword = logpassword;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
