package chess.domain.dao;

import chess.db.MySQLConnector;
import chess.domain.dto.CommandDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandDao {

    public CommandDao() {}

    public void insertAll(CommandDatabase commandDatabase, String historyId) throws SQLException {
        final List<CommandDto> commands = commandDatabase.commands();
        for (CommandDto command : commands) {
            insert(command, historyId);
        }
    }

    public void insert(CommandDto commandDto, String historyId) throws SQLException {
        String query = "INSERT INTO Command (Data, HistoryId) VALUES (?, ?)";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, commandDto.data());
            preparedStatement.setString(2, historyId);
            preparedStatement.executeUpdate();
            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void clear() throws SQLException {
        String query = "DELETE FROM Command";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(String historyId) throws SQLException {
        String query = "DELETE FROM Command WHERE HistoryId = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, historyId);
            preparedStatement.executeUpdate();
            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<CommandDto> selectAllCommands(String historyName) throws SQLException {
        List<CommandDto> commands = new ArrayList<>();
        String query = "SELECT * FROM history H JOIN Command C on H.history_id = C.HistoryId WHERE H.name = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, historyName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                commands.add(new CommandDto(rs.getString("C.Data")));
            }
            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return commands;
    }

    public List<String> selectAllHistoryId() throws SQLException {
        List<String> commands = new ArrayList<>();
        String query = "SELECT * FROM Command";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                commands.add(rs.getString("HistoryId"));

            MySQLConnector.closeConnection(connection);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return commands;
    }
}
