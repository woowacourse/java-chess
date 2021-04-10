package chess.dao;

import chess.entity.User;
import java.util.Optional;

public interface UserDaoInterface {

    void insertUser(String name);

    Optional<User> selectByName(String name);

    void deleteByName(String name);
}
