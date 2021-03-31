package chess.domain.dao;

import chess.db.MySQLConnector;
import chess.domain.dto.CommandDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    private final HistoryDatabase history = new HistoryDatabase();

    public void insert(CommandDto commandDto) throws SQLException {
        int id = 0;
        if (!history.isEmpty()){
            id = history.lastIndex();
        }

        String query = "INSERT INTO history VALUES (?, ?)";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, commandDto.command());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        history.insert(commandDto);
    }

    public void clearCommand() throws SQLException {
        String query = "DELETE FROM history";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        history.clear();
    }

    public void delete(CommandDto commandDto) throws SQLException {
        if (!history.contains(commandDto)) {
            throw new SQLException("해당 명령은 존재하지 않습니다.");
        }
        String id = history.idOf(commandDto);
        String query = "DELETE FROM history WHERE id = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        history.delete(commandDto);
    }

    public List<CommandDto> selectAll(CommandDto commandDto) throws SQLException {
        List<CommandDto> commands = new ArrayList<>();
        String query = "SELECT * FROM history";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                commands.add(new CommandDto(rs.getString("command")));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return commands;
    }
}
