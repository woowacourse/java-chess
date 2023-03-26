package chess.service;

import chess.domain.user.User;
import chess.dto.NameDto;
import chess.repository.UserDao;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(final NameDto nameDto) {
        final User user = userDao.findByName(nameDto);
        if (user != null) {
            throw new IllegalArgumentException("이미 등록된 이름입니다.");
        }
        userDao.save(nameDto);
    }

    public User findByName(final NameDto nameDto) {
        final User user = userDao.findByName(nameDto);
        if (user == null) {
            throw new IllegalArgumentException("해당 이름을 가진 유저가 없습니다.");
        }
        return user;
    }
}
