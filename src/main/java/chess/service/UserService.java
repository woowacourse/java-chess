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
        userDao.insert(userRequestDto.getName());
    }

    public User find(final String name) {
        return userDao.selectByName(name)
            .orElseThrow(() -> new DataNotFoundException(User.class));
    }
}
