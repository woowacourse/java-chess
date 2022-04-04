package chess.dao;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;
import chess.dto.CurrentTurnDto;
import chess.dto.RoomStatusDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao {

    public int save(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        final String sql = "INSERT INTO room (name, game_status, current_turn) VALUE (?, ?, ?)";

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

    public boolean isExistName(final String roomName) {
        final String sql = "SELECT name FROM room WHERE name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);

            try (final ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CurrentTurnDto findCurrentTurnByName(final String roomName) {
        final String sql = "SELECT name, current_turn FROM room WHERE name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);

            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return CurrentTurnDto.from(
                        resultSet.getString("name"),
                        resultSet.getString("current_turn")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RoomStatusDto findStatusByName(final String roomName) {
        final String sql = "SELECT name, game_status FROM room WHERE name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);

            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return RoomStatusDto.from(
                        resultSet.getString("name"),
                        resultSet.getString("game_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int delete(final String roomName) {
        final String sql = "DELETE FROM room WHERE name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(final String roomName, final GameStatus gameStatus, final Color currentTurn) {
        final String sql = "UPDATE room SET game_status = ?, current_turn = ? WHERE name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, gameStatus.getValue());
            statement.setString(2, currentTurn.getValue());
            statement.setString(3, roomName);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateStatusTo(final String roomName, final GameStatus gameStatus) {
        final String sql = "UPDATE room SET game_status = ? WHERE name = ?";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, gameStatus.getValue());
            statement.setString(2, roomName);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
