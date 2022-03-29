package chess.dao;

import chess.Member;
import chess.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
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

    public void save(final Member member) {
        final Connection connection = getConnection();
        final String sql = "insert into member (id, name) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDriver() {
        try {
            Class<?> class2 = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public Member findById(String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name from member where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        final String sql = "select id, name from member";
        List<Member> members = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 members.add(new Member(
                        resultSet.getString("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public Member findWithRoleById(final String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name, role from member join role on member.id = role.user_id where member.id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    new Role(resultSet.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
