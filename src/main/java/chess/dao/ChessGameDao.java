package chess.dao;

import chess.domain.ChessGame;
import chess.domain.TeamColor;
import chess.dto.ChessGameDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long save(final ChessGame game) {
        String queryStatement = "INSERT INTO game (turn, is_end) VALUES(?, ?)";

        return jdbcTemplate.saveAndReturnGeneratedKey(
            queryStatement, game.getTeamColor().name(), game.isEnd());
    }

    public void updateStatus(final long gameId, final boolean isEnd) {
        String queryStatement = "UPDATE game SET is_end = ? WHERE game_id = ?";

        jdbcTemplate.updateOne(queryStatement, isEnd, gameId);
    }

    public void updateTurn(final long gameId, final TeamColor color) {
        String queryStatement = "UPDATE game SET turn = ? WHERE game_id = ?";

        jdbcTemplate.updateOne(queryStatement, color.name(), gameId);
    }

    public Optional<ChessGameDto> findById(final long gameId) {
        String queryStatement = "SELECT game_id, turn FROM game WHERE is_end IS FALSE AND game_id = ?";

        return jdbcTemplate.query(queryStatement, gameId, (resultSet) -> {
            if (resultSet.next()) {
                return Optional.of(new ChessGameDto(resultSet.getLong("game_id"),
                    TeamColor.findByName(resultSet.getString("turn"))));
            }
            return Optional.empty();
        });
    }

    public List<Long> findAllGameId() {
        String queryStatement = "SELECT game_id FROM game WHERE is_end IS FALSE";

        return jdbcTemplate.queryWithNoCondition(queryStatement, (resultSet) -> {
            List<Long> gameIds = new ArrayList<>();
            while (resultSet.next()) {
                gameIds.add(resultSet.getLong("game_id"));
            }
            return gameIds;
        });
    }

}
