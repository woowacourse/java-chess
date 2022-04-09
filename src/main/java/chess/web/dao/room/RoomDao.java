package chess.web.dao.room;

import chess.web.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE = "room"; // 달라짐

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(final String name) {
        final String sql = "" + "INSERT INTO " + TABLE + " (name)" + "  VALUES (?)"; // 달라짐
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate(); // CRUD마다 달라짐.
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("이미 존재하는 이름으로 방을 생성할 수 없습니다. ");
        }
    }

    public void removeById(final int id) {
        final String sql = "" + "DELETE" + "  FROM " + TABLE + "  WHERE id = ?";
        try (final PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room findById(final int id) {
        final String sql = "" + "SELECT id, name, canJoin, currentCamp" + "  FROM " + TABLE + "  WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                statement.close();
                resultSet.close();
                return null;
            }
            return new Room(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBoolean("canJoin"),
                resultSet.getString("currentCamp")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> findAll() {
        final String sql = ""
            + "SELECT id, name, canJoin, currentCamp"
            + "  FROM " + TABLE;
        final List<Room> rooms = new ArrayList<>();
        try (final PreparedStatement statement = getConnection().prepareStatement(sql);
        ) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(new Room(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getBoolean("canJoin"),
                    resultSet.getString("currentCamp")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void updateNameById(final int id, final String name) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET name = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCanJoinById(final int id, final Boolean canJoin) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET canJoin = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setBoolean(1, canJoin);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCampById(final int id, final String currentCamp) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET currentCamp = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, currentCamp);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
