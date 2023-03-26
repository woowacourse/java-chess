package chess.repository;

import chess.domain.user.User;
import java.util.List;

public interface UserDao {

    long save(User user);

    User findByName(String name);

    List<User> findAll();
}
