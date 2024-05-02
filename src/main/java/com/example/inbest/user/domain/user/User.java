package com.example.inbest.user.domain.user;


import com.example.inbest.user.domain.user.value.UserId;
import com.example.inbest.user.domain.user.value.UserInfo;
import com.example.inbest.user.domain.user.value.UserPassword;

public final class User {

    private final UserId email;
    private UserPassword passwords;
    private UserInfo userInfo;


    public User(UserId email, UserPassword passwords, UserInfo userInfo) {
        this.email = email;
        this.passwords = passwords;
        this.userInfo = userInfo;
    }

    public UserId getEmail() {
        return email;
    }

    public UserPassword getPasswords() {
        return passwords;
    }

    public void setPasswords(UserPassword passwords) {
        this.passwords = passwords;
    }
}
