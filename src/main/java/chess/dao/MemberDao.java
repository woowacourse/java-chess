package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(final Member member) {
        final Connection connection = getConnection();
        PreparedStatement statement = null;
        final String sql = "insert into member (id, name) values (?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Member findById(final String id) {
        final Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String sql = "select id, name from member where id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(resultSet.getString("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String sql = "select id, name from member";
        final List<Member> members = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(new Member(resultSet.getString("id"), resultSet.getString("name")));
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
