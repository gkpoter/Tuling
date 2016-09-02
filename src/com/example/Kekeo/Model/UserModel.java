package com.example.Kekeo.Model;

/**
 * Created by dy on 2016/9/2.
 */
public class UserModel {

    private String UserPhone;
    private Integer state;
    private String msg;
    private String CheckCode;

    public String getCheckCode() {
        return CheckCode;
    }

    public void setCheckCode(String checkCode) {
        CheckCode = checkCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}
