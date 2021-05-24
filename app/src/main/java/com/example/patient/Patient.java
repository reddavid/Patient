package com.example.patient;

import java.util.List;

public class Patient {

    private List<String> name;
    private String telecom;
    private String gender;
    private String birthDate;
    private String address;
    private String maritalStatus;
    private String email;
    private Contact contact;

    public Patient() {}

    public Patient(List<String> name, String telecom, String gender, String birthDate, String address, String maritalStatus, String email, Contact contact) {
        this.name = name;
        this.telecom = telecom;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.contact = contact;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public String getTelecom() {
        return telecom;
    }

    public void setTelecom(String telecom) {
        this.telecom = telecom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
