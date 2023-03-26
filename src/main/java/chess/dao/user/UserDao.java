package chess.dao.user;

import chess.entity.UserEntity;

import java.util.Optional;

public interface UserDao {
    Optional<UserEntity> findByName(final String name);
}
