package chess.service;

import chess.domain.user.User;
import chess.repository.UserRepository;
import java.util.List;

public class UserService {

    private static final String NAME_DUPLICATED = "이름이 중복되었습니다.";
    private static final String INVALID_USER = "존재하지 않는 사용자입니다.";

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long signup(final String name) {
        userRepository.findByName(name).ifPresent(user -> {
            throw new IllegalArgumentException(NAME_DUPLICATED);
        });
        User user = new User(name);
        return userRepository.save(user);
    }

    public long login(final String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_USER));
        return user.getId();
    }

    public List<String> findUserNames() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(User::getName)
                .toList();
    }
}
