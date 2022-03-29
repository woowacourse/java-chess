package chess.dao;

import java.sql.*;
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
        // 드라이버 등록
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Member member) {
        final Connection connection = getConnection();
        final String sql = "insert into member (id, name) values (?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Member findById(String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name from member where id=?";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        final String sql = "select id, name from member";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<Member> members = new ArrayList<>();
            while (resultSet.next()) {
                members.add(new Member(
                        resultSet.getString("id"),
                        resultSet.getString("name")));
            }
            return members;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Member findWithRoleById(String id) {
        final String sql = "select id, name, role from member join role on member.id=role.user_id where id=?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    new Role(resultSet.getString("role"))
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
