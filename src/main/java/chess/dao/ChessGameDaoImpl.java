package chess.dao;

import chess.dao.dto.ChessGameDto;
import chess.domain.game.ChessGame;
import chess.domain.game.state.ExecuteState;
import chess.domain.game.state.GameState;
import chess.domain.piece.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessGameDaoImpl implements ChessGameDao {

    private final JdbcTemplate jdbcTemplate;

    public ChessGameDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final ChessGame chessGame) {
        final GameState gameState = chessGame.getGameState();
        final Color turn = gameState.getTurnColor();
        final ExecuteState executeState = gameState.getExecuteState();

        final String query = "insert into chess_game(turn, state) values(?, ?)";
        final List<String> parameters = List.of(turn.name(), ExecuteStateMapper.from(executeState).name());

        jdbcTemplate.executeUpdate(query, parameters);
    }

    @Override
    public List<ChessGameDto> findAll() {
        final String query = "select * from chess_game";

        return jdbcTemplate.executeQuery(query, resultSet -> {
            final List<ChessGameDto> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(ChessGameDto.of(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                ));
            }
            return result;
        }, Collections.emptyList());
    }
}
