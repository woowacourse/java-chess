package chess.service;

import chess.dao.user.UserDao;
import chess.domain.user.User;
import chess.entity.UserEntity;

import java.util.Optional;

public final class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public Long getUserId(final String name) {
        final User user = User.create(name);
        final String userName = user.getName();
        final Optional<UserEntity> userEntity = userDao.findByName(userName);
        if (userEntity.isPresent()) {
            return userEntity.get().getId();
        }
        return insert(name);
    }

    private Long insert(final String name) {
        return userDao.insert(name);
    }
}
