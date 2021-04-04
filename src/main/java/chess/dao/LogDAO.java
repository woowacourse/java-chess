package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    public void createLog(final String roomId, final String startPoint, final String endPoint) throws SQLException {
        String query = "INSERT INTO log (room_id, start_position, end_position) VALUES (?, ?, ?)";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try (connection; statement) {
            statement.setString(1, roomId);
            statement.setString(2, startPoint);
            statement.setString(3, endPoint);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void deleteLogByRoomId(final String roomId) throws SQLException {
        String query = "DELETE FROM log WHERE room_id = ?";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try (connection; statement) {
            statement.setString(1, roomId);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<String[]> allLogByRoomId(final String roomId) throws SQLException {
        String query = "SELECT start_position, end_position FROM log WHERE room_id = ? ORDER BY register_date";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, roomId);
        ResultSet resultSet = statement.executeQuery();

        List<String[]> logs = new ArrayList<>();

        try (connection; statement; resultSet) {
            while (resultSet.next()) {
                String[] positions = new String[2];
                positions[0] = resultSet.getString("start_position");
                positions[1] = resultSet.getString("end_position");
                logs.add(positions);
            }
        } catch (Exception e) {

        }

        return logs;
    }
}
