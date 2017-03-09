package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;



public class SubmitJobResult {

    private int success;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private JobRequested jobr;

    public JobRequested getJobRequested() {
        return jobr;
    }

    public void setJobRequested(JobRequested jobr) {
        this.jobr = jobr;
    }


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
