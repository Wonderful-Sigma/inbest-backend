package sigma.inbest.adapter.jpa.user;

import org.springframework.http.HttpStatus;
import sigma.inbest.application.global.response.InbestException;
import sigma.inbest.domain.user.User;
import sigma.inbest.domain.user.value.UserId;
import sigma.inbest.domain.user.value.UserInfo;
import sigma.inbest.domain.user.value.UserPassword;

abstract class UserConverter {

    static User toDomain(final UserJpaEntity entity) {
        if(entity == null) {
            return null;
        }
        final UserId userId = new UserId(entity.getEmail());
        final UserPassword userPassword = new UserPassword(entity.getPassword(), entity.getSimplePassword());
        final UserInfo userInfo = new UserInfo(entity.getNickname());

        return User.builder()
                .userId(userId)
                .passwords(userPassword)
                .userInfo(userInfo)
                .build();
    }

    static UserJpaEntity toEntity(final User domain) {
        try {
            return UserJpaEntity.builder()
                    .email(domain.getEmail().email())
                    .password(domain.getPasswords().password())
                    .simplePassword(domain.getPasswords().simplePassword())
                    .nickname(domain.getUserInfo().nickname())
                    .build();
        } catch (NullPointerException e) {
            throw new InbestException(HttpStatus.INTERNAL_SERVER_ERROR, "User에 toEntity 작업 중 NPE가 발생했습니다.");
        }
    }
}
