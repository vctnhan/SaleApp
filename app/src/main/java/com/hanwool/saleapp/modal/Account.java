package com.hanwool.saleapp.modal;

import java.io.Serializable;

/**
 * Created by LynkMieu on 20/08/2016.
 */
public class Account implements Serializable {

    private String userName;
    private String fullName;
    private String email;
    private String password;
    public Account(){

    }
    public Account(String userName, String fullName, String email, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
