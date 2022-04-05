package chess.dao;

import chess.LectureMember;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE = "member"; // 달라짐

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //removeById_by_delete
    public void removeById(final String id) {
        final String sql = ""
            + "DELETE"
            + "  FROM " + TABLE
            + "  WHERE id = ?";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //save_by_insert(C)
    public void save(final LectureMember lectureMember) {
        final String sql = ""
            + "INSERT INTO " + TABLE
            + " (id, name)"
            + "  VALUES (?,?)";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, lectureMember.getId());
            statement.setString(2, lectureMember.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LectureMember findById(final String id) {
        final String sql = "" + "SELECT id, name" + "  FROM " + TABLE + "  WHERE id = ?";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new LectureMember(resultSet.getString("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LectureMember> findAll() {
        final String sql = ""
            + "SELECT id, name"
            + "  FROM " + TABLE;
        final List<LectureMember> lectureMembers = new ArrayList<>();
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lectureMembers.add(new LectureMember(resultSet.getString("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lectureMembers;
    }

    public void updateNameById(final String id, final String name) {
        final Connection connection = getConnection();
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET name = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
