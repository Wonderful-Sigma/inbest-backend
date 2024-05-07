package sigma.inbest.adapter.jpa.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sigma.inbest.application.user.UserDBPort;
import sigma.inbest.domain.user.User;

import java.util.Optional;

import static sigma.inbest.adapter.jpa.user.UserConverter.*;
@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserDBPort {

    private final UserJpaRepository userJpaRepository;
    @Override
    public String findByEmail_for_passwd(String email) {
        //추후에 querydsl 추가
        return toDomain(userJpaRepository.findById(email).orElseThrow()).getPasswords().password();
    }

    @Override
    public User save(User user) {
        return toDomain(userJpaRepository.save(toEntity(user)));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(toDomain(userJpaRepository.findById(email).orElseThrow()));
    }
}
