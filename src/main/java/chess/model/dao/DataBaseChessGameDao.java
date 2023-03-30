package chess.model.dao;

import chess.model.domain.board.Turn;
import chess.model.domain.piece.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseChessGameDao implements ChessGameDao {

    private static final String FIND_ALL_GAME_ID_QUERY = "SELECT id FROM chess_game";
    private static final String GENERATE_NEW_GAME_QUERY = "INSERT INTO CHESS_GAME VALUES (null, ?)";
    private static final String UPDATE_TURN_QUERY = "UPDATE CHESS_GAME SET TURN = ? WHERE id = ?";
    private static final String DELETE_BOARD_QUERY = "DELETE FROM chess_game WHERE id = ?";
    private static final String LOAD_TURN_QUERY = "SELECT turn FROM chess_game WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    public DataBaseChessGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Long> findAllId() {
        return jdbcTemplate.executeQuery(FIND_ALL_GAME_ID_QUERY,
                resultSet -> {
                    List<Long> chessGameId = new ArrayList<>();
                    while (resultSet.next()) {
                        chessGameId.add(resultSet.getLong(1));
                    }
                    return chessGameId;
                });
    }

    @Override
    public long generateNewGame() {
        return jdbcTemplate.executeUpdate(GENERATE_NEW_GAME_QUERY,
                resultSet -> {
                    if (resultSet.next()) {
                        return resultSet.getLong(1);
                    }
                    throw new SQLException();
                }, Color.WHITE.name());
    }

    @Override
    public void updateTurn(final Turn turn, final long gameId) {
        jdbcTemplate.executeUpdate(UPDATE_TURN_QUERY, turn.getTurn().name(), gameId);
    }

    @Override
    public Turn loadTurn(final long gameId) {
        return jdbcTemplate.executeQuery(LOAD_TURN_QUERY,
                resultSet -> {
                    if (resultSet.next()) {
                        final String colorString = resultSet.getString(1);
                        return new Turn(Color.valueOf(colorString));
                    }
                    throw new SQLException();
                }, gameId);
    }

    @Override
    public void deleteGame(final long gameId) {
        jdbcTemplate.executeUpdate(DELETE_BOARD_QUERY, gameId);
    }
}
