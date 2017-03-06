package com.example.nikkialonzo.grabahand;

/**
 * Created by JONAS on 2/27/2017.
 */

public class LoginResult {

    private int success;
    private Admin admin;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
