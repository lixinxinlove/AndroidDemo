package com.lee.androiddemo.bean;

import java.io.Serializable;

/**
 * Created by android on 2017/6/22.
 */

public class UserEntity implements Serializable {

    private String userName;
    private int userAge;


    public UserEntity(String userName, int userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
