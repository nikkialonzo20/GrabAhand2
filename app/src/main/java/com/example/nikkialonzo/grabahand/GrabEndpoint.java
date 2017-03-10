package com.example.nikkialonzo.grabahand;



import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface GrabEndpoint {

    @POST("/grab/register-user") //postRegisterUser
    Call<UserRegisterResult> registerUser(@Body UserInfo userInfo);

    @POST("/grab/submit-job")
    Call<SubmitJobResult> registerJob(@Body com.example.nikkialonzo.grabahand.JobInfo jobInfo);

    @GET("/grab/jobs/{job_id}")
    Call<RetrieveJobResults> retrieveJobs(@Path("job_id") int id);

    @POST("/grab/login-admin")
    Call<LoginResult> loginUser(@Body LoginInfo loginInfo);

    @GET("/grab/accept-job/{id}")
    Call<AcceptJobResult> acceptJob(@Path("id") int id);

    @GET("/grab/decline-job/{id}")
    Call<DeclineJobResult> declineJob(@Path("id") int id);

    @GET("/grab/job-status/{id}")
    Call<JobStatusResult> checkStatus(@Path("id") int id);

    @GET("/grab/finish-job/{id}")
    Call<FinishJobResult> finishJob(@Path("id") int id);
}





