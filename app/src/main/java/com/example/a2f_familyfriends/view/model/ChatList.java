package com.example.a2f_familyfriends.view.model;

public class ChatList {

    private String ProfileIMG;
    private String UserID;
    private String UserName;
    private String Message;
    private String Time;


    public ChatList() {
    }

    public ChatList(String userID,String userName, String message, String time, String profileIMG) {
        UserID = userID;
        UserName = userName;
        Message = message;
        Time = time;
        ProfileIMG = profileIMG;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getProfileIMG() {
        return ProfileIMG;
    }

    public void setProfileIMG(String profile) {
        ProfileIMG = getProfileIMG();
    }
}
