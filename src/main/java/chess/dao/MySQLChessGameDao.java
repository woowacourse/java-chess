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
    private static final String EMPTY_PROGRESS_EXCEPTION_MESSAGE = "[ERROR] 저장된 진행 정보가 없습니다.";

    private final JdbcContext jdbcContext;

    public MySQLChessGameDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    @Override
    public Board findBoard() {
        Map<Position, Piece> board = BoardFactory.createEmptyBoard();
        board.putAll(findSquares());
        return new Board(board);
    }

    private Map<Position, Piece> findSquares() {
        final String query = "SELECT * FROM board";
        return jdbcContext.select(query, resultSet -> {
            Map<Position, Piece> squares = new HashMap<>();
            while (resultSet.next()) {
                squares.put(getPosition(resultSet), getPiece(resultSet));
            }
            validateEmptyProgress(squares);
            return squares;
        });
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

    private static void validateEmptyProgress(Map<Position, Piece> squares) {
        if (squares.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_PROGRESS_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public GameState findGameState() {
        final String query = "SELECT * FROM game_state";
        return jdbcContext.select(query, resultSet -> {
            validateEmptyResultSet(resultSet);
            return RunningStateMapper.map(resultSet.getString("state"));
        });
    }

    private void validateEmptyResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            throw new IllegalArgumentException(EMPTY_PROGRESS_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void saveChessGame(Board board, GameState gameState) {
        jdbcContext.transaction(() -> {
            deleteAllBoard();
            deleteGameState();
            saveBoard(board.getBoard());
            saveGameState(gameState);
        });
    }

    private void saveBoard(Map<Position, Piece> board) {
        final String query = "INSERT INTO board VALUES(%s, %s, \"%s\", \"%s\")";
        List<String> queries = board.entrySet().stream()
                .filter(entry -> !entry.getValue().isRoleOf(Role.EMPTY))
                .map(entry -> String.format(query, entry.getKey().getX(), entry.getKey().getY(),
                        entry.getValue().getRole().name(), entry.getValue().getTeam().name()))
                .collect(toList());
        jdbcContext.insertBulk(queries);
    }

    private void saveGameState(GameState gameState) {
        String query = "INSERT INTO game_state VALUES(?)";
        jdbcContext.insert(query, RunningStateMapper.map(gameState));
    }

    public void deleteAllBoard() {
        String query = "DELETE FROM board";
        jdbcContext.update(query);
    }

    public void deleteGameState() {
        String query = "DELETE FROM game_state";
        jdbcContext.update(query);
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
