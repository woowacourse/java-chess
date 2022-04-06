package chess.dao;

import chess.domain.user.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User findById(String id);

    List<User> findAll();

    void deleteById(String id);

    void deleteAll();
}
