package chess.service;

import chess.domain.user.User;
import chess.repository.UserDao;

public class UserService {

    // TODO: 도메인 로직인가?
    private static final String NAME_DUPLICATED = "이름이 중복되었습니다.";

    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public long signup(String name) {
        userDao.findByName(name).ifPresent(user -> {
            throw new IllegalArgumentException(NAME_DUPLICATED);
        });
        User user = new User(name);
        return userDao.save(user);
    }
}
