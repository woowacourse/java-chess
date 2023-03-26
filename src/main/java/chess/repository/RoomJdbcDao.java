package chess.repository;

import chess.db.JdbcTemplate;
import chess.domain.room.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomJdbcDao implements RoomDao {
    private final JdbcTemplate jdbcTemplate;

    public RoomJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final String roomName, final int userId) {
        jdbcTemplate.executeUpdate(
                "INSERT INTO room (name, finished, user_id) values (?, ?, ?)",
                roomName,
                true,
                userId
        );
    }

    @Override
    public List<Room> findAllByUserId(final int userId) {
        return jdbcTemplate.query("select * from room where user_id = ?", resultSet -> {
            final List<Room> result = new ArrayList<>();
            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String name = resultSet.getString("name");
                final boolean finished = resultSet.getBoolean("finished");
                final int findUserId = resultSet.getInt("user_id");
                result.add(new Room(id, name, finished, findUserId));
            }
            return result;
        }, userId);
    }

    @Override
    public Room findById(final int roomId) {
        return jdbcTemplate.query("select * from room where id = ?", resultSet -> {
            if (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String name = resultSet.getString("name");
                final boolean finished = resultSet.getBoolean("finished");
                final int findUserId = resultSet.getInt("user_id");
                return new Room(id, name, finished, findUserId);
            }
            return null;
        }, roomId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.executeUpdate("DELETE FROM room");
    }
}
