package chess.dao;

import chess.Member;
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
    private static final String TABLE = "member";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final int ID_COLUMN_NUMBER = 1;
    private static final int NAME_COLUMN_NUMBER = 2;
    private static final int NAME_COLUMN_NUMBER_ON_UPDATE = 1;
    private static final int ID_COLUMN_NUMBER_ON_UPDATE = 2;

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
            statement.setString(ID_COLUMN_NUMBER, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //save_by_insert(C)
    public void save(final Member member) {
        final String sql = ""
            + "INSERT INTO " + TABLE
            + " (id, name)"
            + "  VALUES (?,?)";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(ID_COLUMN_NUMBER, member.getId());
            statement.setString(NAME_COLUMN_NUMBER, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member findById(final String id) {
        final String sql = "" + "SELECT id, name" + "  FROM " + TABLE + "  WHERE id = ?";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(ID_COLUMN_NUMBER, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new Member(resultSet.getString(ID_COLUMN), resultSet.getString(NAME_COLUMN));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> findAll() {
        final String sql = ""
            + "SELECT id, name"
            + "  FROM " + TABLE;
        final List<Member> members = new ArrayList<>();
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(new Member(resultSet.getString("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void updateNameById(final String id, final String name) {
        final Connection connection = getConnection();
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET name = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(NAME_COLUMN_NUMBER_ON_UPDATE, name);
            preparedStatement.setString(ID_COLUMN_NUMBER_ON_UPDATE, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
