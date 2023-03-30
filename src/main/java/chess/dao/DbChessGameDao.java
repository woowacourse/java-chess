package chess.dao;

import chess.dao.connection.ConnectionGenerator;
import chess.domain.piece.property.Color;
import chess.exception.ChessDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbChessGameDao implements ChessGameDao {

    private final ConnectionGenerator connectionGenerator;

    public DbChessGameDao(final ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    @Override
    public int makeGameRoom(final Color initialTurnColor) {
        final String query = "INSERT INTO gameRooms SET " +
                "turn_color = ?";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, initialTurnColor.name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ChessDbException();
        }
        return findLastCreatedRoomId();
    }

    private int findLastCreatedRoomId() {
        final String query = "SELECT MAX(room_id) AS last_room_id FROM gameRooms";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("last_room_id");

        } catch (SQLException e) {
            throw new ChessDbException();
        }
    }

    @Override
    public Color findCurrentTurnColor(final int gameRoomId) {
        final String query = "SELECT turn_color FROM gameRooms WHERE room_id = ?";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameRoomId);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String turnColorName = resultSet.getString("turn_color");
                return Color.valueOf(turnColorName);
            }
        } catch (SQLException e) {
            throw new ChessDbException();
        }
        return null;
    }

    @Override
    public void updateCurrentTurnColor(final int gameRoomId, final Color turnColor) {
        final String query = "UPDATE gameRooms SET turn_color = ? WHERE room_id = ?";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turnColor.name());
            preparedStatement.setInt(2, gameRoomId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ChessDbException();
        }
    }

    @Override
    public void removeGameDataFromDb(final int gameRoomId) {
        final String query = "DELETE FROM gameRooms where room_id = ?";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameRoomId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ChessDbException();
        }
    }
}
