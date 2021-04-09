package chess.service;

import chess.domain.web.User;
import chess.repository.UserDao;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findUserByName(String name) {
        return userDao.findByName(name);
    }

    public int addUserIfNotExist(String name) {
        Optional<User> userByName = findUserByName(name);
        return userByName.map(User::getUserId).orElseGet(() -> userDao.addUser(new User(name)));
    }

    public boolean isUserExist(int userId) {
        return userDao.findUserById(userId).isPresent();
    }
}
