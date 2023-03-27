package chess.dao;

import chess.dto.PlayerDto;

import java.util.Optional;

public class PlayerDao {

    public Optional<PlayerDto> findByName(final String name) {
        final var query = "SELECT * FROM player WHERE name = ?";

        final RowMapper<PlayerDto> mapper = resultSet -> {
            if (resultSet.next()) {
                return PlayerDto.of(
                        resultSet.getInt(1),
                        resultSet.getString(2)
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
