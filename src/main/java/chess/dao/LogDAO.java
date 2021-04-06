package chess.dao;

import chess.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class LogDAO {

    public void createLog(final String roomId, final String startPoint, final String endPoint) {
        String query = "INSERT INTO log (room_id, start_position, end_position) VALUES (?, ?, ?)";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomId);
            statement.setString(2, startPoint);
            statement.setString(3, endPoint);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("log 생성이 실패했습니다!");
        }
    }

    public void deleteLogByRoomId(final String roomId) {
        String query = "DELETE FROM log WHERE room_id = ?";

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("log 삭제를 실패했습니다!");
        }
    }

    public List<String[]> allLogByRoomId(final String roomId) {
        List<String[]> logs = new ArrayList<>();

        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = allLogStatement(connection, roomId);
             ResultSet resultSet = statement.executeQuery()) {
            addLog(logs, resultSet);
        } catch (SQLException e) {
            throw new DataAccessException("log load를 실패했습니다!");
        }

        return logs;
    }

    private void addLog(final List<String[]> logs, final ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String[] positions = new String[2];
            positions[0] = resultSet.getString("start_position");
            positions[1] = resultSet.getString("end_position");
            logs.add(positions);
        }
    }

    private PreparedStatement allLogStatement(final Connection connection, final String roomId) throws SQLException {
        String query = "SELECT start_position, end_position FROM log WHERE room_id = ? ORDER BY register_date";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roomId);
        return statement;
    }
}
