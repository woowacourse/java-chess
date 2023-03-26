package chess.dao.chess;

import chess.database.JdbcTemplate;
import chess.entity.ChessGameEntity;

import java.util.Optional;

public class ChessGameDaoImpl implements ChessGameDao {
    private final JdbcTemplate jdbcTemplate;

    public ChessGameDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ChessGameEntity> findByUserId(final Long userId) {
        final String query = "SELECT * FROM chess_game WHERE user_id = ?";
        return jdbcTemplate.findOne(query, (resultSet -> new ChessGameEntity(
                resultSet.getLong("chess_game_id"),
                resultSet.getString("current_camp"),
                resultSet.getLong("user_id"))
        ), String.valueOf(userId));
    }
}
