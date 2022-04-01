package chess.dao;

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
        } catch (final Exception e) {
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
        final String sql = "insert into member (id,name) values (?,?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Member findById(String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name from member where id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                resultSet.getString("id"),
                resultSet.getString("name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Member> findAll() {
        final List<Member> members = new ArrayList<>();
        final Connection connection = getConnection();
        final String sql = "select id, name from member";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            members.add(new Member(
                resultSet.getString("id"),
                resultSet.getString("name")
            ));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return members;
    }

    public Member findWithRoleById(String id) {
        final Connection connection = getConnection();
        final String sql = "select member.id, name, role from member join role on member.id = role.id where member.id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                resultSet.getString("id"),
                resultSet.getString("name"),
                new Role(resultSet.getString("role"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
