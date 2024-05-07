package sigma.inbest.domain.user;


import lombok.Builder;
import sigma.inbest.domain.user.value.UserPassword;
import sigma.inbest.domain.user.value.UserId;
import sigma.inbest.domain.user.value.UserInfo;

public final class User {

    private final UserId email;
    private UserPassword passwords;
    private UserInfo userInfo;


    @Builder
    public User(UserId userId, UserPassword passwords, UserInfo userInfo) {
        this.email = userId;
        this.passwords = passwords;
        this.userInfo = userInfo;
    }

    public UserId getEmail() {
        return email;
    }

    public UserPassword getPasswords() {
        return passwords;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setPasswords(UserPassword passwords) {
        this.passwords = passwords;
    }
}
