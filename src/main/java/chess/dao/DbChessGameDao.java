package chess.dao;

import chess.domain.piece.property.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbChessGameDao implements ChessGameDao {

    private final int gameRoomId;

    public DbChessGameDao(final int gameRoomId) {
        this.gameRoomId = gameRoomId;
    }

    @Override
    public void makeGameRoom(final Color initialTurnColor) {
        final String query = "INSERT INTO gameRooms SET " +
                "room_id = ?, " +
                "turn_color = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, gameRoomId);
            preparedStatement.setString(2, initialTurnColor.name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Color findCurrentTurnColor() {
        final String query = "SELECT turn_color FROM gameRooms WHERE room_id = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameRoomId);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String turnColorName = resultSet.getString("turn_color");
                return Color.valueOf(turnColorName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateCurrentTurnColor(final Color turnColor) {
        final String query = "UPDATE gameRooms SET turn_color = ? WHERE room_id = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turnColor.name());
            preparedStatement.setInt(2, gameRoomId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeGameDataFromDb() {
        final String query = "DELETE FROM gameRooms where room_id = ?";
        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameRoomId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getGameRoomId() {
        return gameRoomId;
    }
}
