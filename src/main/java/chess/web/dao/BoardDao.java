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

    public void save(GameStateDto gameStateDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "insert into board (id, turn) values (?, ?)";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);
            statement.setString(2, gameStateDto.getTurn());
            statement.execute();
            closeResources(statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Color find() {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select * from board where id = ?";
        Color color = Color.WHITE;

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);
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

    public void update(GameStateDto gameStateDto) {
        final Connection connection = DBConnector.getConnection();
        final String sql = "update board set turn = ? where id = 1";

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameStateDto.getTurn());
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
