package com.example.nikkialonzo.grabahand;

import java.util.ArrayList;

/**
 * Created by JONAS on 2/27/2017.
 */

public class JobStatusResult {

    private int success;

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
