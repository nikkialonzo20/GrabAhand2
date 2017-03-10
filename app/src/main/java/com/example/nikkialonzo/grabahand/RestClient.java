package com.example.nikkialonzo.grabahand;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String BASE_URL = "http://192.163.1.120:8000";
    private GrabEndpoint apiService;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(GrabEndpoint.class);
    }

    public GrabEndpoint getApiService() {
        return apiService;
    }

}
