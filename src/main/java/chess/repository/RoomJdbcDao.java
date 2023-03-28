package chess.repository;

import chess.db.JdbcTemplate;
import chess.db.RowMapper;
import chess.domain.room.Room;
import java.util.List;
import java.util.Optional;

public class RoomJdbcDao implements RoomDao {
    private final RowMapper<Room> rowMapper = resultSet -> {
        final int id = resultSet.getInt("id");
        final String name = resultSet.getString("name");
        final int findUserId = resultSet.getInt("user_id");
        return new Room(id, name, findUserId);
    };
    private final JdbcTemplate jdbcTemplate;

    public RoomJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final String roomName, final int userId) {
        jdbcTemplate.executeUpdate(
                "INSERT INTO room (name, user_id) values (?, ?)",
                roomName,
                userId
        );
    }

    @Override
    public List<Room> findAllByUserId(final int userId) {
        return jdbcTemplate.query("select * from room where user_id = ?", rowMapper, userId);
    }

    @Override
    public Optional<Room> findById(final int roomId) {
        return jdbcTemplate.queryForSingleResult("select * from room where id = ?", rowMapper, roomId);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.executeUpdate("DELETE FROM room");
    }
}
