package chess.domain.dao;

import chess.db.DriveManager;
import chess.domain.dto.CommandDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandDao {

    public CommandDao() {
    }

    public void insert(CommandDto commandDto, Integer historyId) throws SQLException {
        String query = "INSERT INTO Command (data, history_id) VALUES (?, ?)";
        try (Connection connection = DriveManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, commandDto.data());
            preparedStatement.setInt(2, historyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<CommandDto> selectAllCommands(String id) throws SQLException {
        List<CommandDto> commands = new ArrayList<>();
        String query = "SELECT * FROM history H JOIN Command C on H.history_id = C.history_id WHERE H.history_id = ? AND H.is_end = false";
        try (Connection connection = DriveManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    commands.add(new CommandDto(rs.getString("C.Data")));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return commands;
    }
}
