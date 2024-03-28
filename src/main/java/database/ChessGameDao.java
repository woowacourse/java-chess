package database;

import java.sql.Connection;
import java.sql.SQLException;
import model.Camp;

public class ChessGameDao {

    private static final String TABLE = "chessgame";

    private final Connection connection;

    public ChessGameDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Camp camp) {
        final var query = "INSERT INTO " + TABLE + " VALUES(?)";
        try {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, camp.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Camp find() {
        final var query = "SELECT * FROM " + TABLE;
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Camp.valueOf(resultSet.getString("current_turn"));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Camp camp) {
        final var query =
                "UPDATE " + TABLE + " SET current_turn = ? WHERE current_turn = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, camp.name());
            preparedStatement.setString(2, camp.toggle().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        final var query = "DELETE FROM " + TABLE;
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
