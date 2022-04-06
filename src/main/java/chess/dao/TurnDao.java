package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(final String team) {
        final Connection connection = getConnection();
        final String sql = "insert into turn (team) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, team);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        final Connection connection = getConnection();
        final String sql = "delete from turn";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String find() {
        final Connection connection = getConnection();
        final String sql = "select team from turn";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("team");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("[ERROR] 일치하는 데이터가 없습니다.");
    }

    public void update(String nowTurn, String nextTurn) {
        final Connection connection = getConnection();
        final String sql = "update turn set team = ? where team = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nextTurn);
            statement.setString(2, nowTurn);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
