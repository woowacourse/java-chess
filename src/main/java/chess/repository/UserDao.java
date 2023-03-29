package chess.repository;

import chess.domain.user.User;
import java.util.Optional;

public interface UserDao {

    void save(final String name);

    Optional<User> findByName(final String name);

    void deleteAll();
}
