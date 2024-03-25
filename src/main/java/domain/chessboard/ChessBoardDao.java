package domain.chessboard;

import domain.piece.Piece;
import domain.piece.PieceMaker;
import domain.square.Square;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessBoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private final String database;

    public ChessBoardDao() {
        this.database = DATABASE;
    }

    private ChessBoardDao(final String database) {
        this.database = database;
    }

    static ChessBoardDao test() {
        return new ChessBoardDao("chess_test");
    }

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + database + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addSquarePiece(final Square square, final Piece piece) {
        final var query = "INSERT INTO board VALUES(null, ?, ?, ?, ?)";
        try (final var preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());
            preparedStatement.setString(3, piece.pieceType().name());
            preparedStatement.setString(4, piece.team().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Piece findBySquare(final Square square) {
        final var query = "SELECT * FROM board WHERE file = (?) AND `rank` = (?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());

            System.out.println(preparedStatement.toString());

            final var resultSet = preparedStatement.executeQuery();

            final boolean next = resultSet.next();
            System.out.println(next);
            if (next) {
                return PieceMaker.of(
                        resultSet.getString("piece_type"),
                        resultSet.getString("team")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void update(final Square square, final Piece piece) {
        final var query = "UPDATE board SET piece_type = (?), team = (?) " +
                "where file = (?) AND `rank` = (?)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.pieceType().name());
            preparedStatement.setString(2, piece.team().name());
            preparedStatement.setString(3, square.file().name());
            preparedStatement.setString(4, square.rank().name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBySquare(final Square square) {
        final var query = "DELETE FROM board where file = (?) AND `rank` = (?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
