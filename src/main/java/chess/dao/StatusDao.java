package chess.dao;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.StatusDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDao {

    public int saveByRoomName(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        final String sql = "INSERT INTO Status(Room_Name, GameStatus, CurrentTurn) VALUES (?, ?, ?)";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);
            statement.setString(2, gameStatus.getValue());
            statement.setString(3, currentTurn.getValue());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteByRoomName(final String roomName) {
        final String sql = "DELETE FROM Status WHERE Room_Name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public StatusDto findByRoomName(final String roomName) {
        final String sql = "SELECT * FROM Status WHERE Room_Name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);

            try (final ResultSet resultSet = statement.executeQuery()) {

                if (!resultSet.next()) {
                    return null;
                }
                return StatusDto.of(
                        resultSet.getString("GameStatus"),
                        resultSet.getString("CurrentTurn")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
