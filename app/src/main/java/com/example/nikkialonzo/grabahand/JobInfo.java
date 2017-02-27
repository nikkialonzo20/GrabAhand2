package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JONAS on 2/27/2017.
 */

public class JobInfo {

    @SerializedName("user_id")
    int userId;

    @SerializedName("job_id")
    int jobId;

    @SerializedName("long")
    private double lon;

    private double lat;

    private String address;

    public JobInfo(int userId, int jobId, double lon, double lat, String address) {
        this.userId = userId;
        this.jobId = jobId;
        this.lon = lon;
        this.lat = lat;
        this.address = address;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
