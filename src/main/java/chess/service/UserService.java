package chess.service;

import chess.dao.UserDao;
import chess.domain.user.User;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }
}
