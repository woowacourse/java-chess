package chess.dao;

import chess.domain.player.Player;

public class PlayerDao {

    private static final String NOT_EXIST_PLAYER_ERROR_MESSAGE = "참여자가 존재하지 않습니다";

    public static Player findByName(final String name) {
        final var query = "SELECT * FROM player WHERE name = ?";

        final RowMapper<Player> mapper = resultSet -> {
            if (resultSet.next()) {
                return Player.of(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
            throw new RuntimeException(NOT_EXIST_PLAYER_ERROR_MESSAGE);
        };

        return JdbcTemplate.select(query, mapper, name);
    }

    public static Player create(final String name) {
        final var query = "INSERT INTO player(name) VALUES (?)";

        JdbcTemplate.executeUpdate(query, name);

        return findByName(name);
    }
}
