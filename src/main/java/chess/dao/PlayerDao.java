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
            return create(name);
        };

        return JdbcTemplate.select(query, mapper, name);
    }

    private static Player create(final String name) {
        final var query = "INSERT INTO player(name) VALUES (?)";

        final int id = JdbcTemplate.insertAndReturnKey(query, name);

        return Player.of(id, name);
    }
}
