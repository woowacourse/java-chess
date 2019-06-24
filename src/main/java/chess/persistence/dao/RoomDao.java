package chess.persistence.dao;

import chess.persistence.dto.RoomDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {
    private static class RoomDaoSql {
        private static final String INSERT = "INSERT INTO room(title) VALUES(?)";
        private static final String SELECT_BY_ID = "SELECT id, title FROM room WHERE id=?";
        private static final String SELECT_BY_TITLE = "SELECT id, title FROM room WHERE title=?";
        private static final String SELECT_LATEST_N = "SELECT id, title FROM room ORDER BY id DESC LIMIT ?";
        private static final String DELETE_BY_ID = "DELETE FROM room WHERE id=?";
    }

    private DataSource dataSource;

    public RoomDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addRoom(RoomDto room) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, room.getTitle());
            query.executeUpdate();
            try (ResultSet rs = query.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        }
    }

    public Optional<RoomDto> findById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);
            try (ResultSet rs = query.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                RoomDto room = mapResult(rs);
                return Optional.of(room);
            }
        }
    }

    private RoomDto mapResult(ResultSet rs) throws SQLException {
        RoomDto room = new RoomDto();
        room.setId(rs.getLong("id"));
        room.setTitle(rs.getString("title"));
        return room;
    }

    public Optional<RoomDto> findByTitle(String title) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.SELECT_BY_TITLE)) {
            query.setString(1, title);
            try (ResultSet rs = query.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(mapResult(rs));
            }
        }
    }

    public List<RoomDto> findLatestN(int topN) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.SELECT_LATEST_N)) {
            query.setInt(1, topN);
            try (ResultSet rs = query.executeQuery()) {
                List<RoomDto> findRooms = new ArrayList<>();
                while (rs.next()) {
                    findRooms.add(mapResult(rs));
                }
                return findRooms;
            }
        }
    }

    public int deleteById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        }
    }
}
