package com.example.aaahome.service.login;

public class RequestLogin {
    String email;
    String pwd;
    String pid;

    public RequestLogin() {
    }

    public RequestLogin(String email, String pwd, String pid) {
        this.email = email;
        this.pwd = pwd;
        this.pid = pid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
