package chess.web.dao.room;

import chess.web.Room;
import chess.web.dao.JdbcTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private static final String TABLE = "room";
    private final Connection connection;

    public RoomDaoImpl(final Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(final String name) {
        final String sql = ""
            + "INSERT INTO " + TABLE + " (name)"
            + "  VALUES (?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate(); // CRUD마다 달라짐.
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("이미 존재하는 이름으로 방을 생성할 수 없습니다. ");
        }
    }

    @Override
    public int findIdByName(final String name) {
        final String sql = ""
            + "SELECT id"
            + "  FROM " + TABLE
            + "  WHERE name = ?";
        try (PreparedStatement statement = JdbcTemplate.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                statement.close();
                resultSet.close();
                return 0;
            }
            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void removeById(final int id) {
        final String sql = ""
            + "DELETE"
            + "  FROM " + TABLE
            + "  WHERE id = ?";
        try (final PreparedStatement statement = JdbcTemplate.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room findById(final int id) {
        final String sql = ""
            + "SELECT id, name, canJoin, currentCamp"
            + "  FROM " + TABLE
            + "  WHERE id = ?";
        try (PreparedStatement statement = JdbcTemplate.getConnection().prepareStatement(sql)) {
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
                resultSet.getInt("canJoin"),
                resultSet.getString("currentCamp")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> findAll() {
        final String sql = ""
            + "SELECT id, name, canJoin, currentCamp"
            + "  FROM " + TABLE;
        final List<Room> rooms = new ArrayList<>();
        try (final PreparedStatement statement = JdbcTemplate.getConnection().prepareStatement(sql);
        ) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rooms.add(new Room(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("canJoin"),
                    resultSet.getString("currentCamp")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void updateNameById(final int id, final String name) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET name = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoom(final int roomId,
                           final int canJoin,
                           final String currentCamp) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET canJoin = ? , currentCamp = ?"
            + "  WHERE id = ?";
        try (PreparedStatement preparedStatement = JdbcTemplate.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, canJoin);
            preparedStatement.setString(2, currentCamp);
            preparedStatement.setInt(3, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
