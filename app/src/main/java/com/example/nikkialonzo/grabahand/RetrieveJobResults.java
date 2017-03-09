package com.example.nikkialonzo.grabahand;

import java.util.ArrayList;

/**
 * Created by JONAS on 2/27/2017.
 */

public class RetrieveJobResults {

    private int success;
    private ArrayList<Job> jobs;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }
}
