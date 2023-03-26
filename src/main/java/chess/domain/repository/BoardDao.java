package chess.domain.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDao {

    public Long saveBoard(String turn, String state) {
        String saveBoardWithDefaultQuery = "INSERT INTO board (turn, state) VALUES (?, ?)";

        try (Connection connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(saveBoardWithDefaultQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setString(2, state);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }

            throw new SQLException();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE FROM board";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
