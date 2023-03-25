package database;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.RowMapper;

public final class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        jdbcTemplate.executeUpdate("INSERT INTO user VALUES(?, ?)", user.userId(), user.name());
    }

    public User findByUserId(String userId) {
        return jdbcTemplate.query("SELECT * FROM user WHERE user_id = ?", getUserRowMapper(), userId);
    }

    private RowMapper<User> getUserRowMapper() {
        return resultSet -> {
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("name")
                );
            }
            return null;
        };
    }

    public void update(User user, String name) {
        jdbcTemplate.executeUpdate("UPDATE user SET name = ? WHERE user_id = ?", name, user.userId());
    }

    public void delete(String userId) {
        jdbcTemplate.executeUpdate("DELETE FROM user WHERE user_id = ?", userId);
    }
}
