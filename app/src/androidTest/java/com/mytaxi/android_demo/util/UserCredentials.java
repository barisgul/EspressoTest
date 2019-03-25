package com.mytaxi.android_demo.util;

public class UserCredentials {

    public String userName;

    public String getUserName()
    {
        return userName;
    }

    public String password;

    public String getPassword()
    {
        return password;
    }

    public UserCredentials(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

}
