package chess.repository;

import chess.domain.ChessGame;
import chess.domain.TeamColor;
import chess.dto.ChessGameDto;
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

    public Optional<ChessGameDto> findLastGame() {
        String queryStatement = "SELECT * FROM game WHERE is_end IS FALSE ORDER BY game_id DESC LIMIT 1";

        return jdbcTemplate.queryWithNoCondition(queryStatement, (resultSet) -> {
            if (resultSet.next()) {
                return Optional.of(new ChessGameDto(resultSet.getLong("game_id"),
                    TeamColor.findByName(resultSet.getString("turn"))));
            }
            return Optional.empty();
        });
    }

}
