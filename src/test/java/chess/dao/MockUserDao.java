package chess.dao;

import chess.dao.user.UserDao;
import chess.entity.UserEntity;

import java.util.Optional;

public class MockUserDao implements UserDao {
    @Override
    public Optional<UserEntity> findByName(final String name) {
        if (name.equals("journey")) {
            return Optional.of(new UserEntity(1L, "journey"));
        }
        return Optional.empty();
    }

    @Override
    public Long insert(final String name) {
        if (name.equals("journey")) {
            return 1L;
        }
        return 2L;
    }
}
