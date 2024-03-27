package chess.repository;

import chess.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    long save(User user);

    Optional<User> findByName(String name);

    List<User> findAll();
}
