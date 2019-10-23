package com.example.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userName;
    private String password;
    private String email;
    private String nickname;
    private String regTime;

    public User() {
        super();
    }

    public User(String userName, String password, String email, String nickname, String regTime) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.regTime = regTime;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + this.userName + '\'' +
                ", password='" + this.password + '\'' +
                ", email='" + this.email + '\'' +
                ", nickname='" + this.nickname + '\'' +
                ", regTime='" + this.regTime + '\'' +
                "}"
                ;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }


}
