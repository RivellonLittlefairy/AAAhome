package com.example.aaahome.POJO;

public class User {
    private int uid;
    private String uname;
    private String pwd;
    private String email;
    private String uhead;

    public User(int uid, String uname, String pwd, String email, String uhead) {
        this.uid = uid;
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        this.uhead = uhead;
    }

    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUhead() {
        return uhead;
    }

    public void setUhead(String uhead) {
        this.uhead = uhead;
    }
}
