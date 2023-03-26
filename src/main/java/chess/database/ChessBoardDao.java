package chess.database;

import database.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessBoardDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addBoard(int gameIdx, int file, int rank, String pieceType) {
        final var query = "INSERT INTO ChessBoard VALUES(?, ?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, gameIdx);
            preparedStatement.setInt(2, file);
            preparedStatement.setInt(3, rank);
            preparedStatement.setString(4, pieceType);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findPiece(int gameIdx, int file, int rank) {
        final var query = "select peiceType\n"
                + "from ChessBoard\n"
                + "where gameIdx = ? and bFile = ? and bRank = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameIdx);
            preparedStatement.setInt(2, file);
            preparedStatement.setInt(3, rank);

            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("peiceType");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void movePiece(int gameIdx, int targetFile, int targetRank, String pieceType) {
        final var query = "update ChessBoard\n"
                + "set peiceType = ?\n"
                + "where gameIdx = ? and bFile = ? and bRank = ?;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceType);
            preparedStatement.setInt(2, gameIdx);
            preparedStatement.setInt(3, targetFile);
            preparedStatement.setInt(4, targetRank);
            final var resultSet = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
