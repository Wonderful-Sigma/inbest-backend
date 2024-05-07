package sigma.inbest.application.user;

import sigma.inbest.domain.user.User;

import java.util.Optional;

public interface UserDBPort {
    String findByEmail_for_passwd(String email);

    User save(User user);

    Optional<User> findByEmail(String email);
}
