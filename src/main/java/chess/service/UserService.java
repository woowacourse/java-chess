package chess.service;

import chess.dao.UserDao;
import chess.domain.user.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public List<User> findAll(String id) {
        return userDao.findAll();
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void merge(User user) {
        User findUser = userDao.findById(user.getId());
        if (findUser == null) {
            userDao.save(user);
            return;
        }
        userDao.update(findUser);
    }
}
