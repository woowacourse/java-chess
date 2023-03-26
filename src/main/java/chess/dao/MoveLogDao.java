package chess.dao;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.PieceName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MoveLogDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void insertMove(final Board board,
                                  final Position source,
                                  final Position target,
                                  final Piece sourcePiece,
                                  final Piece targetPiece) {
        final var query = "INSERT INTO move_log(board_id, source_position, target_position, source_piece, target_piece)" +
                "VALUES(?, ?, ?, ?, ?)";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, board.getId());
            preparedStatement.setString(2, source.getCoordinate());
            preparedStatement.setString(3, target.getCoordinate());
            preparedStatement.setString(4, PieceName.findByPiece(sourcePiece));
            preparedStatement.setString(5, PieceName.findByPiece(targetPiece));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
