package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import domain.PieceNameConverter;
import domain.board.ChessBoard;
import domain.board.Square;
import domain.piece.Piece;

public class ChessBoardDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(ChessBoard chessBoard) {
        final var query = "INSERT INTO chess_board VALUES(?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Map.Entry<Square, Piece> squareAndPiece : chessBoard.getBoard().entrySet()) {
                preparedStatement.setString(1, squareAndPiece.getKey().toString());
                preparedStatement.setString(2, PieceNameConverter.convert(squareAndPiece.getValue()));
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Piece select(Square square) {
        final var query = "SELECT piece FROM chess_board WHERE square = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.toString());

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return PieceNameConverter.convert(resultSet.getString(1));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(ChessBoard chessBoard) {
        final var query = "UPDATE chess_board SET piece = ? WHERE square = ?";
        for (Map.Entry<Square, Piece> squareAndPiece : chessBoard.getBoard().entrySet()) {
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, PieceNameConverter.convert(squareAndPiece.getValue()));
                preparedStatement.setString(2, squareAndPiece.getKey().toString());
                preparedStatement.execute();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete() {
        final var query = "DELETE FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasData() {
        final var query = "SELECT COUNT(*) AS COUNT FROM chess_board";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT") != 0;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
