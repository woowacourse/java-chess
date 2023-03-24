package chess.dao;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class MySQLChessGameDao implements ChessGameDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE_CONNECTION_EXCEPTION_MESSAGE = "[ERROR] 데이터베이스의 연결에 문제가 발생했습니다.";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Board findBoard() {
        Map<Position, Piece> squares = BoardFactory.createEmptyBoard();
        final String query = "SELECT * FROM board";
        try (final Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(query)) {
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()) {
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                Role role = Role.valueOf(resultSet.getString("role"));
                Team team = Team.valueOf(resultSet.getString("team"));
                squares.put(Position.of(x, y), PieceFactory.of(role, team));
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
        return new Board(squares);
    }

    @Override
    public GameState findGameState() {
        final String query = "SELECT * FROM game_state";
        try (final Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(query)) {
            ResultSet resultSet = psmt.executeQuery();
            resultSet.next();
            RunningStateEnum state = RunningStateEnum.valueOf(resultSet.getString("state"));
            return state.gameState;
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void saveChessGame(Board board, GameState gameState) {
        saveBoard(board.getBoard());
        saveGameState(gameState);
    }

    private void saveBoard(Map<Position, Piece> board) {
        final String query = "INSERT INTO board VALUES(?,?,?,?)";
        try (final Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(query)) {
            for (Entry<Position, Piece> entry : board.entrySet()) {
                if (!entry.getValue().isRoleOf(Role.EMPTY)) {
                    psmt.setInt(1, entry.getKey().getX());
                    psmt.setInt(2, entry.getKey().getY());
                    psmt.setString(3, entry.getValue().getRole().name());
                    psmt.setString(4, entry.getValue().getTeam().name());
                    psmt.execute();
                }
            }
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    private void saveGameState(GameState gameState) {
        final String query = "INSERT INTO game_state VALUES(?)";
        try (final Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setString(1, RunningStateEnum.ofState(gameState).name());
            psmt.execute();
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void deleteAllBoard() {
        final String query = "DELETE FROM board";
        try (final Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void deleteGameState() {
        final String query = "DELETE FROM game_state";
        try (final Connection con = getConnection();
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
        }
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
