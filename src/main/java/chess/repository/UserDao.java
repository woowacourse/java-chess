package chess.repository;

import chess.infra.connection.JdbcTemplate;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(String userId) {
        String query = "INSERT INTO user (user_id) VALUES (?)";
        jdbcTemplate.executeUpdate(query, userId);
    }

    public void deleteByUserId(String userId) {
        String query = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.executeUpdate(query, userId);
    }
}
