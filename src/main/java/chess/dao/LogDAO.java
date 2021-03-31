package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    private final ConnectDB connectDB;

    public LogDAO(ConnectDB connectDB) {
        this.connectDB = connectDB;
    }

    public void createLog(String roomId, String startPoint, String endPoint) throws SQLException {
        String query = "INSERT INTO log (room_id, start_position, end_position) VALUES (?, ?, ?)";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setString(1, roomId);
        statement.setString(2, startPoint);
        statement.setString(3, endPoint);
        statement.executeUpdate();
    }

    public void deleteLogByRoomId(String roomId) throws SQLException {
        String query = "DELETE FROM log WHERE room_id = ?";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setString(1, roomId);
        statement.executeUpdate();
    }

    public List<String[]> allLogByRoomId(String roomId) throws SQLException {
        String query = "SELECT start_position, end_position FROM log WHERE room_id = ? ORDER BY register_date";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setString(1, roomId);
        ResultSet resultSet = statement.executeQuery();

        List<String[]> logs = new ArrayList<>();

        while (resultSet.next()) {
            String[] positions = new String[2];
            positions[0] = resultSet.getString("start_position");
            positions[1] = resultSet.getString("end_position");
            logs.add(positions);
        }
        return logs;
    }
}
