package com.example.nikkialonzo.grabahand;

import com.google.gson.annotations.SerializedName;


public class UserRegisterResult {

    private int success;

    @SerializedName("user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
