package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.ConnectDB.CONNECTION;

public class LogDAO {

    public void createLog(final String roomId, final String startPoint, final String endPoint) throws SQLException {
        String query = "INSERT INTO log (room_id, start_position, end_position) VALUES (?, ?, ?)";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setString(1, roomId);
        statement.setString(2, startPoint);
        statement.setString(3, endPoint);
        statement.executeUpdate();
        statement.close();
    }

    public void deleteLogByRoomId(final String roomId) throws SQLException {
        String query = "DELETE FROM log WHERE room_id = ?";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setString(1, roomId);
        statement.executeUpdate();
        statement.close();
    }

    public List<String[]> allLogByRoomId(final String roomId) throws SQLException {
        String query = "SELECT start_position, end_position FROM log WHERE room_id = ? ORDER BY register_date";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setString(1, roomId);
        ResultSet resultSet = statement.executeQuery();

        List<String[]> logs = new ArrayList<>();

        while (resultSet.next()) {
            String[] positions = new String[2];
            positions[0] = resultSet.getString("start_position");
            positions[1] = resultSet.getString("end_position");
            logs.add(positions);
        }
        statement.close();
        return logs;
    }
}
