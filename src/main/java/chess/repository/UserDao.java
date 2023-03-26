package chess.repository;

import chess.domain.user.User;

public interface UserDao {

    void save(final String name);

    User findByName(final String name);

    void deleteAll();
}
