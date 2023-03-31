package chess.repository.user;

import chess.mysql.JdbcTemplate;
import chess.mysql.RowMapper;
import java.util.Optional;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(String userName) {
        String query = "INSERT INTO user (user_name) VALUES (?)";
        return jdbcTemplate.save(query, userName);
    }

    public Optional<UserDto> findUserIdIfExist(String userName) {
        String query = "SELECT * FROM user WHERE user_name = ?";
        return jdbcTemplate.query(query, userIdMapper(), userName);
    }

    private RowMapper<Optional<UserDto>> userIdMapper() {
        return resultSet -> {
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                return Optional.of(new UserDto(userId));
            }
            return Optional.empty();
        };
    }

    public void deleteByUserName(String userName) {
        String query = "DELETE FROM user WHERE user_name = ?";
        jdbcTemplate.executeUpdate(query, userName);
    }
}
