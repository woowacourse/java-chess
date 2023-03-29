package chess.service;

import chess.domain.user.User;
import chess.repository.UserDao;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(final String name) {
        final Optional<User> user = userDao.findByName(name);
        if (user.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이름입니다.");
        }
        userDao.save(name);
    }

    public User findByName(final String name) {
        final User user = userDao.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 유저가 없습니다."));
        return user;
    }
}
