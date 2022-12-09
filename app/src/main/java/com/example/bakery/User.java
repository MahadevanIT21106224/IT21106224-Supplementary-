package com.example.bakery;

public class User {
    private String UserID;
    private String Password;
    private String UserType;

    public User ()
    {

    }

    public User(String userID, String password, String userType) {
        UserID = userID;
        Password = password;
        UserType = userType;
    }



    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }



}
