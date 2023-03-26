package chess.repository;

import chess.db.JdbcTemplate;
import chess.domain.user.User;

public class UserJdbcDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final String name) {
        jdbcTemplate.executeUpdate("INSERT INTO user (name) VALUES (?)", name);
    }

    @Override
    public User findByName(final String name) {
        return jdbcTemplate.query("SELECT * FROM user WHERE name = ?", resultSet -> {
            if (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String findName = resultSet.getString("name");
                return new User(id, findName);
            }
            return null;
        }, name);
    }

    public void deleteAll() {
        jdbcTemplate.executeUpdate("DELETE FROM user");
    }
}
