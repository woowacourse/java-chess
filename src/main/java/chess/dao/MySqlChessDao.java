package chess.dao;

import chess.database.MySqlConnector;
import chess.dto.CommandDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlChessDao implements ChessDao {

    @Override
    public void addCommand(CommandDto command) {
        String query = "INSERT INTO commands VALUES (?)";
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, command.get());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void clearCommands() {
        String query = "TRUNCATE commands";
        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<CommandDto> selectCommands() {
        String query = "SELECT * FROM commands";
        List<CommandDto> commands = new ArrayList<>();

        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                CommandDto commandDto = new CommandDto(rs.getString("command"));
                commands.add(commandDto);
            }
            return commands;
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return commands;
    }
}