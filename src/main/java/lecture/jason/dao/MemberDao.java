package lecture.jason.dao;

import lecture.jason.domain.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private static final String URL = "jdbc:mysql://localhost:3306/wooa-chess"; // database-name
    private static final String USERNAME = "wooa-chess";
    private static final String PASSWORD = "1234";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Member member) {
        final Connection connection = getConnection();
        final String sql = "insert member(member_id, member_name) values(?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Member findById(int id) {
        final Connection connection = getConnection();
        final String sql = "select * from `member` where member_id = ?";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(
                    resultSet.getInt("member_id"),
                    resultSet.getString("member_name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        final String sql = "select * from `member`";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            final List<Member> findMembers = new ArrayList<>();
            while (resultSet.next()) {
                final Member findMember = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("member_name")
                );
                findMembers.add(findMember);
            }
            return findMembers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteAll() {
        final Connection connection = getConnection();
        final String sql = "delete from `member`";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteById(int id) {
        final Connection connection = getConnection();
        final String sql = "delete from `member` where member_id = ?";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
