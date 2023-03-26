package chess.dao;

import chess.domain.player.Player;

public class PlayerDao {

    public static Player findByName(final String name) {
        final var query = "SELECT * FROM player WHERE name = ?";

        final RowMapper<Player> mapper = resultSet -> {
            if (resultSet.next()) {
                return Player.of(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
            return null;
        };

        return JdbcTemplate.select(query, mapper, name);
    }

    public static Player create(final String name) {
        final var query = "INSERT INTO player(name) VALUES (?)";

        JdbcTemplate.executeUpdate(query, name);

        return findByName(name);
    }
}
