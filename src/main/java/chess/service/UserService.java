package chess.service;

import chess.domain.web.User;
import chess.repository.UserDao;
import java.sql.SQLException;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByName(String name) throws SQLException {
        return userDao.findByName(name);
    }

    public int findUserIdByName(String name) throws SQLException {
        return userDao.findByName(name).getUserId();
    }

    public void addUserIfNotExist(String name) throws SQLException {
        if(findUserByName(name) == null) {
            userDao.addUser(new User(name));
        }
    }
}
