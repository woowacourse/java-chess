package chess.dao;

import chess.domain.player.Player;

import java.util.Optional;

public class PlayerDao {

    public Optional<Player> findByName(final String name) {
        final var query = "SELECT name FROM player WHERE name = ?";

        final RowMapper<Player> mapper = resultSet -> {
            if (resultSet.next()) {
                return Player.of(
                        resultSet.getString(1)
                );
            }
            return null;
        };

        return Optional.ofNullable(JdbcTemplate.select(query, mapper, name));
    }

    private void create(final String name) {
        final var query = "INSERT INTO player(name) VALUES (?)";

        JdbcTemplate.executeQuery(query, name);
    }

    public void createIfNotExist(final String name) {
        if (findByName(name).isEmpty()) {
            create(name);
        }
    }
}
