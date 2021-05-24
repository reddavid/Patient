package com.example.patient;

import java.util.List;

public class Contact {

    private List<String> name;
    private String telecom;
    private String gender;

    public Contact() {

    }

    public Contact(List<String> name, String telecom, String gender) {
        this.name = name;
        this.telecom = telecom;
        this.gender = gender;
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
}