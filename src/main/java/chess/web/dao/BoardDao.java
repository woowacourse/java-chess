package chess.web.dao;

import chess.domain.Color;
import chess.web.DBConnector;
import chess.web.dto.GameStateDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDao {

    public void save(int userId, GameStateDto gameStateDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into board (player_id, turn) values (?, ?)";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setString(2, gameStateDto.getTurn());
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Color getTurn(int boardId) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select turn from board where id = ?";
        Color color = Color.WHITE;

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, boardId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                color = Color.findByValue(resultSet.getString("turn"));
            }
            closeResources(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return color;
    }

    public int getBoardIdByPlayer(int plyerId) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select id from board where player_id = ?";
        int id = 0;

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, plyerId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            closeResources(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void update(int boardId, GameStateDto gameStateDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "update board set turn = ? where id = ?";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameStateDto.getTurn());
            statement.setInt(2, boardId);
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(Statement statement, Connection connection) throws SQLException {
        statement.close();
        connection.close();
    }

    private void closeResources(ResultSet resultSet, Statement statement, Connection connection)
            throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
}
