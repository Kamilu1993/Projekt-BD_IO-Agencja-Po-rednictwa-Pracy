package com.company.Model;


public class UserInfo {
    private String userName;
    private int userId;
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }


    public UserInfo(int userId, String userName, UserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return userName;
    }
}
