package chess.dao;

import chess.vo.PointsVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class CommandDAO {
    private final String server;
    private final String database;
    private final String option;
    private final String userName;
    private final String password;

    public CommandDAO(final String server, final String database, final String option, final String userName, final String password) {
        this.server = server;
        this.database = database;
        this.option = option;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public void addCommand(final String roomId, final String startPoint, final String endPoint) {
        String query = "INSERT INTO command(room_id, start_point, end_point) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, roomId);
            preparedStatement.setString(2, startPoint);
            preparedStatement.setString(3, endPoint);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<PointsVO> getCommandsByRoomId(final String roomId) throws SQLException {
        String query = "SELECT start_point, end_point FROM command WHERE room_id = ? ORDER BY command_time";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getPoints(resultSet);
        }
    }

    private List<PointsVO> getPoints(final ResultSet resultSet) throws SQLException {
        List<PointsVO> points = new ArrayList<>();
        while (resultSet.next()) {
            points.add(new PointsVO(resultSet.getString("start_point"), resultSet.getString("end_point")));
        }
        resultSet.close();
        return points;
    }

    public void deleteCommandsByRoomId(final String roomId) {
        String query = "DELETE FROM command WHERE room_id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
