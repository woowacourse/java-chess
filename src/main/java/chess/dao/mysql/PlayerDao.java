package chess.dao.mysql;

import chess.dao.connect.JdbcTemplate;
import chess.dao.dto.PlayerDto;

public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final PlayerDto playerDto) {
        final String query = "INSERT INTO Player (color, pieces) VALUES (?,?)";
        return jdbcTemplate.executeInsertQuery(query,
                preparedStatement -> {
                    preparedStatement.setString(1, playerDto.getColorName());
                    preparedStatement.setString(2, playerDto.getPieces());
                });
    }

    public PlayerDto findById(final Long id) {
        final String query = "SELECT id, color, pieces FROM Player where id=?";
        return jdbcTemplate.executeQuery(query,
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> new PlayerDto(
                        resultSet.getLong("id"),
                        resultSet.getString("color"),
                        resultSet.getString("pieces")
                ));
    }

    public void update(final PlayerDto playerDto) {
        final String query = "UPDATE Player SET pieces=? WHERE id=?";
        jdbcTemplate.executeQuery(query,
                preparedStatement -> {
                    preparedStatement.setString(1, playerDto.getPieces());
                    preparedStatement.setLong(2, playerDto.getId());
                });
    }

    public void remove(final long id) {
        final String query = "DELETE FROM Player WHERE id=?";
        jdbcTemplate.executeQuery(query,
                preparedStatement -> {
                    preparedStatement.setLong(1, id);
                });
    }
}
