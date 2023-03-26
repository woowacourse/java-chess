package chess.dao;

import chess.domain.game.ChessGame;
import chess.domain.player.Player;
import chess.domain.room.ChessRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChessRoomDao {

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

    public static ChessRoom findByPlayer(final Player player) {
        final var query = "SELECT id, game_id, player_id, state FROM chess_room WHERE player_id = ? AND state != \"END\"";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, player.getId());

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ChessRoom.of(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChessRoom create(final ChessGame chessGame, final Player player) {
        final var query = "INSERT INTO chess_room(game_id, player_id) VALUES (?, ?)";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, chessGame.getId());
            preparedStatement.setInt(2, player.getId());
            preparedStatement.executeUpdate();
            return findByPlayer(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
