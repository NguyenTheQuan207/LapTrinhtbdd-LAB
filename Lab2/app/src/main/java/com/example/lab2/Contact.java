package com.example.lab2;

public class Contact  {
    private int id;
    private String name;
    private String phone_number;
    private Boolean status;

    public Contact(int id, String name, String phone_number, Boolean status) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
