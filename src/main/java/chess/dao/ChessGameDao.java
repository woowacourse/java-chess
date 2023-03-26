package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.room.ChessRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessGameDao {

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

    public static ChessGame create(final Board board) {
        final var query = "INSERT INTO chess_game VALUES (?, DEFAULT)";
        try (final var connection = getConnection()) {
            final var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, board.getId());

            prepareStatement.executeQuery();

            final var generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return ChessGame.of(
                        generatedKeys.getInt(1),
                        board.getId()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static ChessGame findById(final int id) {
        final var query = "SELECT * FROM chess_game WHERE id = ?";
        try (final var connection = getConnection()) {
            final var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, id);

            final var resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return ChessGame.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
