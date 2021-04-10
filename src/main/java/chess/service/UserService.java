package chess.service;

import chess.dao.UserDaoInterface;
import chess.dto.user.UserRequestDto;
import chess.entity.User;
import chess.service.exception.DataNotFoundException;

public class UserService {

    private final UserDaoInterface userDao;

    public UserService(final UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    public void add(final UserRequestDto userRequestDto) {
        userDao.insertUser(userRequestDto.getName());
    }

    public User find(final UserRequestDto userRequestDto) {
        return userDao.selectByName(userRequestDto.getName())
            .orElseThrow(() -> new DataNotFoundException(User.class));
    }
}
