package chess.dao.chess;

import chess.database.JdbcTemplate;
import chess.domain.chess.CampType;
import chess.entity.ChessGameEntity;

import java.util.Optional;

public final class ChessGameDaoImpl implements ChessGameDao {
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

    @Override
    public Long save(final ChessGameEntity chessGameEntity) {
        final String query = "INSERT INTO chess_game(current_camp, user_id) VALUES (?, ?)";

        return jdbcTemplate.executeUpdate(query,
                chessGameEntity.getCurrentCamp(),
                String.valueOf(chessGameEntity.getUserId()));
    }

    @Override
    public void updateCurrentCampById(final Long id, final CampType currentCamp) {
        final String query = "UPDATE chess_game SET current_camp = ? WHERE chess_game_id = ?";

        jdbcTemplate.executeUpdate(query,
                currentCamp.name(),
                String.valueOf(id));
    }
}
