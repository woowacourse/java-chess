package chess.service;

import chess.domain.user.User;
import chess.repository.UserRepository;

public class UserService {

    private static final String NAME_DUPLICATED = "이름이 중복되었습니다.";

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long signup(String name) {
        userRepository.findByName(name).ifPresent(user -> {
            throw new IllegalArgumentException(NAME_DUPLICATED);
        });
        User user = new User(name);
        return userRepository.save(user);
    }
}
