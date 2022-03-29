package chess.domain.dao;

import chess.domain.Member;
import chess.domain.piece.Role;
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
        final String sql = "insert into member (id, name) values (?,?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getId());
            preparedStatement.setString(2, member.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member findById(String id) {
        final Connection connection = getConnection();
        final String sql = "select id, name from member where id = ?";
        Member member = null;
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                member = toMember(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public List<Member> findAll() {
        final Connection connection = getConnection();
        final String sql = "select id, name from member";
        final List<Member> members = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                members.add(toMember(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    private Member toMember(ResultSet resultSet) throws SQLException {
        return new Member(
                resultSet.getString("id"),
                resultSet.getString("name"));
    }

    public Member findRoleById(final String id) {
        final Connection connection = getConnection();
        final String sql = "select member.id, member.name, role.role from member join role on member.id = role.member_id where id = ?";
        Member member = null;
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                member = new Member(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        new Role(resultSet.getString("role")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
