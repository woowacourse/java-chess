package chess.service;

import chess.dao.user.UserDao;
import chess.domain.user.User;
import chess.entity.UserEntity;

import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void validateDuplicate(final String name) {
        final User user = User.create(name);
        final String userName = user.getName();
        final Optional<UserEntity> userEntity = userDao.findByName(userName);
        if (userEntity.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다. 다른 이름을 입력해 주세요.");
        }
    }

    public Long insert(final String name) {
        return userDao.insert(name);
    }
}
