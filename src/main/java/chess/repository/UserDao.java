package chess.repository;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.RowMapper;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(String userId) {
        if (findUserIdIfExist(userId) != null) {
            return;
        }
        String query = "INSERT INTO user (user_id) VALUES (?)";
        jdbcTemplate.executeUpdate(query, userId);
    }

    private String findUserIdIfExist(String userId) {
        String query = "SELECT user_id FROM user WHERE user_id = ?";
        return jdbcTemplate.query(query, userIdMapper(), userId);
    }

    private RowMapper<String> userIdMapper() {
        return resultSet -> {
            if (resultSet.next()) {
                return resultSet.getString("user_id");
            }
            return null;
        };
    }

    public void deleteByUserId(String userId) {
        String query = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.executeUpdate(query, userId);
    }
}
