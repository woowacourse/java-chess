package chess.dao;

import chess.domain.player.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PlayerDao {

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

    public static Player findByName(final String name) {
        final var query = "SELECT id, name FROM player WEHRE name = ?";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Player.of(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Player create(final String name) {
        final var query = "INSERT INTO player VALUES (?)";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return findByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
