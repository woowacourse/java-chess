package chess.domain.repository;

import chess.domain.repository.entity.BoardEntity;
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

    public boolean existBoard() {
        final var query = "SELECT * FROM board";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public BoardEntity findExistBoard() {
        final var query = "SELECT * FROM board";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new BoardEntity(
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
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
