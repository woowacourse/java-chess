package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class LogDAO {

    public void createLog(final String roomId, final String startPoint, final String endPoint) throws Exception {
        String query = "INSERT INTO log (room_id, start_position, end_position) VALUES (?, ?, ?)";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try (connection; statement) {
            statement.setString(1, roomId);
            statement.setString(2, startPoint);
            statement.setString(3, endPoint);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("log 생성이 실패했습니다!");
        }
    }

    public void deleteLogByRoomId(final String roomId) throws Exception {
        String query = "DELETE FROM log WHERE room_id = ?";
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        try (connection; statement) {
            statement.setString(1, roomId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("log 삭제를 실패했습니다!");
        }
    }

    public List<String[]> allLogByRoomId(final String roomId) throws Exception {
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
            throw new SQLException("log load를 실패했습니다!");
        }

        return logs;
    }
}
