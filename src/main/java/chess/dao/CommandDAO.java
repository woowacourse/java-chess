package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class CommandDAO {
    public Connection getConnection() {
        Connection connection = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

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

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("connection 오류:" + e.getMessage());
        }
    }

    public void addCommand(final String roomId, final String startPoint, final String endPoint) throws SQLException {
        String query = "INSERT INTO command(room_id, start_point, end_point) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, roomId);
        preparedStatement.setString(2, startPoint);
        preparedStatement.setString(3, endPoint);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<List<String>> getCommandsByRoomId(final String roomId) throws SQLException {
        String query = "SELECT start_point, end_point FROM command WHERE room_id = ? ORDER BY command_time";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, roomId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<List<String>> points = getPoints(resultSet);
        preparedStatement.close();
        return points;
    }

    private List<List<String>> getPoints(final ResultSet resultSet) throws SQLException {
        List<List<String>> points = new ArrayList<>();
        while (resultSet.next()) {
            List<String> positions = new ArrayList<>();
            positions.add(resultSet.getString("start_point"));
            positions.add(resultSet.getString("end_point"));
            points.add(positions);
        }
        return points;
    }

    public void deleteCommandsByRoomId(final String roomId) throws SQLException {
        String query = "DELETE FROM command WHERE room_id = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, roomId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
