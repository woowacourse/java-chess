package chess.dao.user;

import chess.database.JdbcTemplate;
import chess.entity.UserEntity;

import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserEntity> findByName(final String name) {
        final String query = "SELECT * FROM user WHERE user_name = ?";
        return jdbcTemplate.findOne(query, (resultSet -> new UserEntity(
                resultSet.getLong("user_id"),
                resultSet.getString("user_name"))
        ), name);
    }

    @Override
    public Long insert(final String name) {
        final String query = "INSERT INTO user(user_name) VALUES(?)";
        return jdbcTemplate.executeUpdate(query, name);
    }
}
