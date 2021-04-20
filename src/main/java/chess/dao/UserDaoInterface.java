package chess.dao;

import chess.domain.user.User;
import java.util.Optional;

public interface UserDaoInterface {

    void insert(String name);

    Optional<User> selectById(long id);

    Optional<User> selectByName(String name);

    void updateResult(long winnerId, long loserId);

    void deleteByName(String name);
}
