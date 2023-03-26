package chess.dao;

import static java.util.stream.Collectors.toList;

import chess.dao.template.JdbcContext;
import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.game.state.GameState;
import chess.game.state.running.BlackCheckedState;
import chess.game.state.running.BlackTurnState;
import chess.game.state.running.WhiteCheckedState;
import chess.game.state.running.WhiteTurnState;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLChessGameDao implements ChessGameDao {
    private final JdbcContext jdbcContext;

    public MySQLChessGameDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @Override
    public Board findBoard(String gameId) {
        Map<Position, Piece> board = BoardFactory.createEmptyBoard();
        board.putAll(findSquares(gameId));
        return new Board(board);
    }

    private Map<Position, Piece> findSquares(String gameId) {
        final String query = "SELECT * FROM board WHERE game_id = ?";
        return jdbcContext.select(query, resultSet -> {
            Map<Position, Piece> squares = new HashMap<>();
            while (resultSet.next()) {
                squares.put(getPosition(resultSet), getPiece(resultSet));
            }
            return squares;
        }, gameId);
    }

    private Position getPosition(ResultSet resultSet) throws SQLException {
        int x = resultSet.getInt("x");
        int y = resultSet.getInt("y");
        return Position.of(x, y);
    }

    private Piece getPiece(ResultSet resultSet) throws SQLException {
        Role role = Role.valueOf(resultSet.getString("role"));
        Team team = Team.valueOf(resultSet.getString("team"));
        return PieceFactory.of(role, team);
    }

    @Override
    public GameState findGameState(String gameId) {
        final String query = "SELECT * FROM state WHERE game_id = ?";
        return jdbcContext.select(query, resultSet -> {
            resultSet.next();
            return RunningStateMapper.map(resultSet.getString("state"));
        }, gameId);
    }

    @Override
    public void saveChessGame(String gameId, Board board, GameState gameState) {
        deleteAllBoard(gameId);
        deleteGameState(gameId);
        saveBoard(gameId, board.getBoard());
        saveGameState(gameId, gameState);
    }

    private void saveBoard(String gameId, Map<Position, Piece> board) {
        final String query = "INSERT INTO board VALUES(%s, %s, %s, \"%s\", \"%s\")";
        List<String> queries = board.entrySet().stream()
                .filter(entry -> !entry.getValue().isRoleOf(Role.EMPTY))
                .map(entry -> String.format(query, gameId, entry.getKey().getX(), entry.getKey().getY(),
                        entry.getValue().getRole().name(), entry.getValue().getTeam().name()))
                .collect(toList());
        jdbcContext.insertBulk(queries);
    }

    private void saveGameState(String gameId, GameState gameState) {
        String query = "INSERT INTO state VALUES(?, ?)";
        jdbcContext.insert(query, gameId, RunningStateMapper.map(gameState));
    }

    @Override
    public void createChessGame(String gameId, Board board, GameState gameState) {
        String query = "INSERT INTO game VALUES(?)";
        jdbcContext.insert(query, gameId);
        saveBoard(gameId, board.getBoard());
        saveGameState(gameId, gameState);
    }

    public void deleteAllBoard(String gameId) {
        String query = "DELETE FROM board WHERE game_id = ?";
        jdbcContext.update(query, gameId);
    }

    public void deleteGameState(String gameId) {
        String query = "DELETE FROM state WHERE game_id = ?";
        jdbcContext.update(query, gameId);
    }

    @Override
    public boolean isExistGame(String gameId) {
        final String query = "SELECT * FROM state WHERE game_id = ? LIMIT 1";
        return jdbcContext.select(query, ResultSet::next, gameId);
    }

    @Override
    public void transaction(Runnable runnable) {
        jdbcContext.transaction(runnable);
    }

    private enum RunningStateMapper {
        WHITE_TURN(WhiteTurnState.STATE),
        BLACK_TURN(BlackTurnState.STATE),
        WHITE_CHECKED(WhiteCheckedState.STATE),
        BLACK_CHECKED(BlackCheckedState.STATE);

        final GameState gameState;

        RunningStateMapper(GameState gameState) {
            this.gameState = gameState;
        }

        public static String map(GameState gameState) {
            return Arrays.stream(values())
                    .filter(runningStateMapper -> runningStateMapper.isSameState(gameState))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new)
                    .name();
        }

        public static GameState map(String gameState) {
            return RunningStateMapper.valueOf(gameState).gameState;
        }

        private boolean isSameState(GameState gameState) {
            return this.gameState == gameState;
        }
    }
}
