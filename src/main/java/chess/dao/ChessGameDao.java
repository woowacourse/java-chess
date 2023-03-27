package chess.dao;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceType;
import chess.domain.position.Position;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class ChessGameDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            throw new RuntimeException("데이터베이스 연결에 실패했습니다.", e);
        }
    }

    public void insert(final Board board) {
        final var query = "INSERT INTO board (position_row, position_column, piece_type, piece_team, turn) VALUES (?, ?, ?, ?, ?)";
        Map<Position, Piece> pieces = board.getBoard();
        Turn turn = board.getTurn();

        for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();

            executeUpdate(query, String.valueOf(position.getRow()), String.valueOf(position.getColumn()),
                    PieceType.from(piece.getClass()).name(), piece.getTeam().name(), turn.name());
        }
    }

    private void executeUpdate(final String query, final String... parameters) {
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (int i = 1; i <= parameters.length; i++) {
                preparedStatement.setString(i, parameters[i - 1]);
            }
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Board findBoard() {
        final var query = "SELECT * FROM board";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.last();
            if (resultSet.getRow() == 0) {
                return null;
            }
            resultSet.beforeFirst();

            Map<Position, Piece> board = new HashMap<>();
            Turn turn = null;

            while (resultSet.next()) {
                int row = Integer.parseInt(resultSet.getString("position_row"));
                int column = Integer.parseInt(resultSet.getString("position_column"));
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                Team pieceTeam = Team.valueOf(resultSet.getString("piece_team"));
                turn = Turn.valueOf(resultSet.getString("turn"));

                Position position = Position.of(row, column);
                Piece piece = pieceType.generatePiece(pieceTeam);

                board.put(position, piece);
            }
            return new Board(board, turn);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBoard(final Board board) {
        final var query = "UPDATE board SET piece_type = ?, piece_team = ?, turn = ? WHERE position_row = ? AND position_column = ?;";

        for (Map.Entry<Position, Piece> entry : board.getBoard().entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();

            executeUpdate(query, PieceType.from(piece.getClass()).name(), piece.getTeam().name(),
                    board.getTurn().name(), String.valueOf(position.getRow()), String.valueOf(position.getColumn()));
        }
    }

    public void deleteAll() {
        final var query = "DELETE FROM board";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
