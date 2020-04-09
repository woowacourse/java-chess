package chess.database;

import chess.web.ChessCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlChessDao implements ChessDao {

    @Override
    public void addCommand(ChessCommand command) {
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
    public List<ChessCommand> selectCommands() {
        String query = "SELECT * FROM commands";
        List<ChessCommand> commands = new ArrayList<>();

        try (Connection connection = MySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                ChessCommand chessCommand = new ChessCommand(rs.getString("command"));
                commands.add(chessCommand);
            }
            return commands;
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return commands;
    }
}