package chess.service;

import chess.dto.NameDto;
import chess.dto.UserDto;
import chess.repository.UserDao;

public class UserService {
    private final UserDao userDao;

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(final NameDto nameDto) {
        userDao.save(nameDto);
    }

    public UserDto findByName(final NameDto nameDto) {
        return userDao.findByName(nameDto);
    }
}
