package chess.domain.dao;

import chess.db.MySQLConnector;
import chess.domain.dto.HistoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {

    public String insert(String name) throws SQLException {
        String query = "INSERT INTO History (Name) VALUES (?)";
        Connection connection = MySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        final String id = resultSet.getString(1);
        MySQLConnector.closeConnection(connection);
        return id;
    }

    public int findIdByName(String name) throws SQLException {
        String query = "SELECT * FROM History WHERE Name = ? AND is_end = false";
        int id = -1;
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();

            if (!rs.next()) return id;
            id = rs.getInt("history_id");

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    public void delete(String name) throws SQLException {
        String query = "DELETE FROM History WHERE Name = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<String> selectActive() throws SQLException {
        List<String> names = new ArrayList<>();
        String query = "SELECT * FROM History WHERE is_end = false";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                names.add(rs.getString("name"));

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return names;
    }

    public HistoryDto findById(String id) throws SQLException {
        Connection connection = MySQLConnector.getConnection();
        String query = "SELECT * FROM History WHERE history_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (!rs.next()) return null;
        return new HistoryDto(rs.getString("name"));
    }

    public void updateEndState(String historyId) throws SQLException {
        Connection connection = MySQLConnector.getConnection();
        String query = "UPDATE History SET is_end = 1 WHERE history_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, historyId);
        preparedStatement.executeUpdate();
    }
}
