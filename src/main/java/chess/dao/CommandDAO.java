package chess.dao;

import chess.dto.PointsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CommandDAO {
    private final DataBaseConnector dataBaseConnector;

    public CommandDAO(final String server, final String database, final String option, final String userName, final String password) {
        dataBaseConnector = new DataBaseConnector(server, database, option, userName, password);
    }

    public void addCommand(final String roomId, final String startPoint, final String endPoint) throws SQLException {
        String query = "INSERT INTO command(room_id, start_point, end_point) VALUES(?, ?, ?)";
        try (
                Connection connection = dataBaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, roomId);
            preparedStatement.setString(2, startPoint);
            preparedStatement.setString(3, endPoint);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<PointsDTO> getCommandsByRoomId(final String roomId) throws SQLException {
        String query = "SELECT start_point, end_point FROM command WHERE room_id = ? ORDER BY command_time";
        try (
                Connection connection = dataBaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getPoints(resultSet);
        }
    }

    private List<PointsDTO> getPoints(final ResultSet resultSet) throws SQLException {
        List<PointsDTO> points = new ArrayList<>();
        while (resultSet.next()) {
            points.add(new PointsDTO(resultSet.getString("start_point"), resultSet.getString("end_point")));
        }
        resultSet.close();
        return points;
    }

    public void deleteCommandsByRoomId(final String roomId) throws SQLException {
        String query = "DELETE FROM command WHERE room_id = ?";
        try (
                Connection connection = dataBaseConnector.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
