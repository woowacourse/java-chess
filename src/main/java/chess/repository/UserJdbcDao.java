package chess.repository;

import chess.db.JdbcTemplate;
import chess.db.RowMapper;
import chess.domain.user.User;
import java.util.Optional;

public class UserJdbcDao implements UserDao {
    private final RowMapper<User> rowMapper = resultSet -> {
        final int id = resultSet.getInt("id");
        final String findName = resultSet.getString("name");
        return new User(id, findName);
    };
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final String name) {
        jdbcTemplate.executeUpdate("INSERT INTO user (name) VALUES (?)", name);
    }

    @Override
    public Optional<User> findByName(final String name) {
        return jdbcTemplate.queryForSingleResult("SELECT * FROM user WHERE name = ?", rowMapper, name);
    }

    public void deleteAll() {
        jdbcTemplate.executeUpdate("DELETE FROM user");
    }
}
