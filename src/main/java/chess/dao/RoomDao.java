package chess.dao;

import chess.entity.Room;
import chess.exception.InsertQueryException;
import chess.exception.SelectQueryException;
import chess.exception.UpdateQueryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoomDao {

    private final Connection connection;

    public RoomDao(Connection connection) {
        this.connection = connection;
    }

    public boolean save(Room room) {
        String sql = "insert into room (turn, name) values (?, ?)";
        boolean isSave = false;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, room.getTurn());
            statement.setString(2, room.getName());
            if (statement.executeUpdate() == 1) {
                isSave = true;
            }
        } catch (SQLException e) {
            throw new InsertQueryException();
        }
        return isSave;
    }

    public Optional<Room> findByName(String name) {
        String sql = "select * from room r where name = ?";
        Room room = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room = new Room(resultSet.getLong("id"),
                        resultSet.getString("turn"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new SelectQueryException();
        }

        return Optional.ofNullable(room);
    }

    public Optional<Room> findById(long id) {
        String sql = "select * from room r where id = ?";
        Room room = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room = new Room(resultSet.getLong("id"),
                        resultSet.getString("turn"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new SelectQueryException();
        }

        return Optional.ofNullable(room);
    }

    public void update(long id, String turn) {
        String sql = "update room set turn = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, turn);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateQueryException();
        }
    }
}
