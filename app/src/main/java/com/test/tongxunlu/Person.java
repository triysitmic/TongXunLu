package com.test.tongxunlu;

import java.io.Serializable;

/**
 * Created by zhengyanda on 2018/4/4.
 */

public class Person implements Serializable {
    private String name;

    private String phoneNumber;

    private String message;

    private String email;

    public Person(String name, String phoneNumber, String message, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.email = email;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean equals(Person p) {
        if (p.name.equals(this.name) &&
                p.phoneNumber.equals(this.phoneNumber) &&
                p.message.equals(this.message) &&
                p.email.equals(this.message) &&
                p.address.equals(this.address)) {
            return true;
        }
        return false;
    }
}
