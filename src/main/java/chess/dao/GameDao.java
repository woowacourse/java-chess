package chess.dao;

import chess.dto.response.GameDto;
import chess.exception.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao extends Dao {

    private static final String TABLE_NAME = "game";
    private static final String WHITE_TURN = "WHITE";
    private static final String BLACK_TURN = "BLACK";

    public GameDto getGame(String gameId) {
        String query = String.format("SELECT turn FROM %s WHERE id = ?", TABLE_NAME);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return GameDto.from(gameId, resultSet.getString("turn"));
        } catch (SQLException e) {
            System.out.println(e);
            throw new DatabaseException();
        }
    }

    public void createGame(String gameId) {
        String query = String.format("INSERT INTO %s VALUES (?, 'WHITE')", TABLE_NAME);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    public void deleteGame(String gameId) {
        String query = String.format("DELETE FROM %s WHERE id = ?", TABLE_NAME);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new DatabaseException();
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

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, turn);
            preparedStatement.setString(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }
}
