package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class JobStatusResult {

    private int success;

    @SerializedName("job_requested")
    private ArrayList<JobRequested> jobRequested;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<JobRequested> getJobRequested() {
        return jobRequested;
    }

    public void setJobRequested(ArrayList<JobRequested> jobRequested) {
        this.jobRequested = jobRequested;
    }


}
