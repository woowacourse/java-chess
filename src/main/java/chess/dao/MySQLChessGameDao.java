package chess.dao;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MySQLChessGameDao implements ChessGameDao {
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
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                Role role = Role.valueOf(resultSet.getString("role"));
                Team team = Team.valueOf(resultSet.getString("team"));
                squares.put(Position.of(x, y), PieceFactory.of(role, team));
            }
            return squares;
        });
    }

    @Override
    public GameState findGameState() {
        final String query = "SELECT * FROM game_state";
        return jdbcContext.select(query, resultSet -> {
            resultSet.next();
            RunningStateEnum state = RunningStateEnum.valueOf(resultSet.getString("state"));
            return state.gameState;
        });
    }

    @Override
    public void saveChessGame(Board board, GameState gameState) {
        saveBoard(board.getBoard());
        saveGameState(gameState);
    }

    private void saveBoard(Map<Position, Piece> board) {
        for (Entry<Position, Piece> entry : board.entrySet()) {
            savePiece(entry.getKey(), entry.getValue());
        }
    }

    private void savePiece(Position position, Piece piece) {
        final String query = "INSERT INTO board VALUES(?,?,?,?)";
        if (!piece.isRoleOf(Role.EMPTY)) {
            jdbcContext.insert(query,
                    position.getX(),
                    position.getY(),
                    piece.getRole().name(),
                    piece.getTeam().name()
            );
        }
    }

    private void saveGameState(GameState gameState) {
        String query = "INSERT INTO game_state VALUES(?)";
        jdbcContext.insert(query, RunningStateEnum.ofState(gameState).name());
    }

    @Override
    public void deleteAllBoard() {
        String query = "DELETE FROM board";
        jdbcContext.update(query);
    }

    @Override
    public void deleteGameState() {
        String query = "DELETE FROM game_state";
        jdbcContext.update(query);
    }

    private enum RunningStateEnum {
        WHITE_TURN(WhiteTurnState.STATE),
        BLACK_TURN(BlackTurnState.STATE),
        WHITE_CHECKED(WhiteCheckedState.STATE),
        BLACK_CHECKED(BlackCheckedState.STATE);

        final GameState gameState;

        RunningStateEnum(GameState gameState) {
            this.gameState = gameState;
        }

        public static RunningStateEnum ofState(GameState gameState) {
            return Arrays.stream(values())
                    .filter(runningStateEnum -> runningStateEnum.isSameState(gameState))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }

        private boolean isSameState(GameState gameState) {
            return this.gameState == gameState;
        }
    }
}
