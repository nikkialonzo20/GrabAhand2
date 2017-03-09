package com.example.nikkialonzo.grabahand;

import java.util.ArrayList;

/**
 * Created by JONAS on 2/27/2017.
 */

public class RetrieveJobResults {

    private int success;
    private ArrayList<Job> job;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<Job> getJobs() {
        return job;
    }

    public void setJobs(ArrayList<Job> job) {
        this.job = job;
    }
}
