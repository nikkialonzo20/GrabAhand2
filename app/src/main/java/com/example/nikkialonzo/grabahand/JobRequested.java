package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JONAS on 3/9/2017.
 */

public class JobRequested {
    private int id;
    private int status;
    private String phone;

    @SerializedName("job_id")
    private String jobId;

    private String name;
    private String address;

    public JobRequested(int id, int status, String phone, String jobId, String name, String address) {
        this.id = id;
        this.status = status;
        this.phone = phone;
        this.jobId = jobId;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
