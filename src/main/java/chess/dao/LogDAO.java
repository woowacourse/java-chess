package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
