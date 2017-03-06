package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JONAS on 2/27/2017.
 */

public class Job {

    private int id;

    @SerializedName("cp_name")
    private String cpName;

    @SerializedName("cp_address")
    private int cpAddress;

    @SerializedName("cp_phone")
    private String cpPhone;

    private String address;

    @SerializedName("long")
    private double lon;

    private double lat;

    @SerializedName("job_id")
    private int jobId;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getCpAddress() {
        return cpAddress;
    }

    public void setCpAddress(int cpAddress) {
        this.cpAddress = cpAddress;
    }

    public String getCpPhone() {
        return cpPhone;
    }

    public void setCpPhone(String cpPhone) {
        this.cpPhone = cpPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}