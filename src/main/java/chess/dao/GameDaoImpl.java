package chess.dao;

import chess.dto.response.ChessGameDto;
import chess.exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDaoImpl extends Dao implements GameDao {

    private static final String TABLE_NAME = "game";
    private static final String WHITE_TURN = "WHITE";
    private static final String BLACK_TURN = "BLACK";

    public ChessGameDto getGame(String gameId) {
        String query = String.format("SELECT turn FROM %s WHERE id = ?", TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean hasNext = resultSet.next();
            if (!hasNext) {
                throw new IllegalArgumentException("해당하는 게임 ID가 존재하지 않습니다.");
            }

            return ChessGameDto.of(gameId, resultSet.getString("turn"));
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void createGame(String gameId) {
        String query = String.format("INSERT INTO %s VALUES (?, 'WHITE')", TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void deleteGame(String gameId) {
        String query = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void updateTurnToWhite(String gameId) {
        updateTurn(gameId, WHITE_TURN);
    }

    public void updateTurnToBlack(String gameId) {
        updateTurn(gameId, BLACK_TURN);
    }

    private void updateTurn(String gameId, String turn) {
        String query = String.format("UPDATE %s SET turn = ? WHERE id = ?", TABLE_NAME);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, turn);
            preparedStatement.setString(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
