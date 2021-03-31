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
    private final CommandDatabase cmdDatabase = new CommandDatabase();

    public void insert(CommandDto commandDto) throws SQLException {
        String query = "INSERT INTO Command (Data, HistoryId) VALUES (?, ?)";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, commandDto.data());
            preparedStatement.setString(2, commandDto.historyId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        cmdDatabase.insert(commandDto);
    }

    public void clear() throws SQLException {
        String query = "DELETE FROM Command";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        cmdDatabase.clear();
    }

    public void delete(CommandDto commandDto) throws SQLException {
        String query = "DELETE FROM Command WHERE HistoryId = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, commandDto.historyId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        cmdDatabase.delete(commandDto);
    }

    public List<CommandDto> selectAll() throws SQLException {
        List<CommandDto> commands = new ArrayList<>();
        String query = "SELECT * FROM history";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                commands.add(new CommandDto(
                        rs.getString("Data"),
                        rs.getString("HistoryId")));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return commands;
    }
}
