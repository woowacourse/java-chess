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

    private Connection loadDriver() {
        try {
            // Class.forName을 해야 static block 이 실행된다.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(final Member member) {
        final Connection connection = getConnection();
        final String sql = "insert into member (id, name) values (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
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
                resultSet.getString("name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        final String sql = "select * from member;";
        List<Member> members = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(new Member(
                    resultSet.getString("id"),
                    resultSet.getString("name")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public Member findWithRoleById(String id) {
        final Connection connection = getConnection();
        final String sql = "select member.id, member.name, role.role "
            + "from member join role on member.id = role.user_id "
            + "where member.id = ?";
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
                new Role(resultSet.getString("role"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.close();
            resultSet.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
