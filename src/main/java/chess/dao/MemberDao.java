package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.Member;
import chess.Role;

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

    private Connection loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Member member) {
        Connection connection = getConnection();
        String sql = "insert into member (id, name) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member findById(String id) {
        Connection connection = getConnection();
        String sql = "select id, name from member where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                resultSet.getString("id"),
                resultSet.getString("name"), new Role("admin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        Connection connection = getConnection();
        String sql = "select id, name from member";
        List<Member> members = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(
                    new Member(
                        resultSet.getString("id"),
                resultSet.getString("name"),
                        new Role("admin"))
                );
                return members;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Member findWithRoleById(String id) {
        Connection connection = getConnection();
        String sql = "" +
            "select id, name, role" +
            " from member join role on member.id = role.member_id" +
            " where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
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
        return null;
    }
}
