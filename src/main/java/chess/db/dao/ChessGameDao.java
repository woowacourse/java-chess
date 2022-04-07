package chess.db.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameDto;
import chess.db.entity.ChessGameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {

    private final DBConnector dbConnector = new DBConnector();
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public int count() {
        connection = dbConnector.getConnection();
        String sql = "SELECT id FROM chess_game ORDER BY id DESC LIMIT 1";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt("id");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return 0;
    }

    public int save(final ChessGame chessGame) {
        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);
        connection = dbConnector.getConnection();
        String sql = "INSERT INTO chess_game (state) VALUES (?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, chessGameDto.getState());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return count();
    }

    public ChessGameEntity find(final int id) {
        connection = dbConnector.getConnection();
        String sql = "SELECT id, state FROM chess_game WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new ChessGameEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("state")
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public void move(final int chessGameId, final String nextState) {
        connection = dbConnector.getConnection();
        String sql = "UPDATE chess_game SET state = ? WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nextState);
            statement.setInt(2, chessGameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete(final int chessGameId) {
        connection = dbConnector.getConnection();
        String sql = "DELETE FROM chess_game WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, chessGameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            closeResultSet();
            closeStatement();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private void closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    private void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
