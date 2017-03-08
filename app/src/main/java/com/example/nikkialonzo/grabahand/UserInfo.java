package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JONAS on 2/27/2017.
 */

public class UserInfo {

    private String address;
    private String name;
    private String email;
    private int phone;
    private String token;

    @SerializedName("cp_name") //camelCase naming convention/
    private String cpName;

    @SerializedName("cp_address")
    private String cpAddress;

    @SerializedName("cp_phone")
    private int cpPhone;

    public UserInfo(String name, String email, int phone, String address, String token, String cpName, String cpAddress, int cpPhone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.token = token;
        this.cpName = cpName;
        this.cpAddress = cpAddress;
        this.cpPhone = cpPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpAddress() {
        return cpAddress;
    }

    public void setCpAddress(String cpAddress) {
        this.cpAddress = cpAddress;
    }

    public int getCpPhone() {
        return cpPhone;
    }

    public void setCpPhone(int cpPhone) {
        this.cpPhone = cpPhone;
    }
}
