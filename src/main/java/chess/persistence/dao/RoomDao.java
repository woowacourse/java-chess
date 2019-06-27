package chess.persistence.dao;

import chess.persistence.AbstractDataSource;
import chess.persistence.dto.RoomDto;

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

    private AbstractDataSource dataSourceFactory;

    public RoomDao(AbstractDataSource dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public long addRoom(RoomDto room) {
        try (Connection conn = dataSourceFactory.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, room.getTitle());
            query.executeUpdate();
            try (ResultSet rs = query.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 추가할 수 없습니다.");
        }
    }

    public Optional<RoomDto> findById(long id) {
        try (Connection conn = dataSourceFactory.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);
            try (ResultSet rs = query.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                RoomDto room = mapResult(rs);
                return Optional.of(room);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 찾을 수 없습니다.");
        }
    }

    public Optional<RoomDto> findByTitle(String title) {
        try (Connection conn = dataSourceFactory.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.SELECT_BY_TITLE)) {
            query.setString(1, title);
            try (ResultSet rs = query.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(mapResult(rs));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 찾을 수 없습니다.");
        }
    }

    private RoomDto mapResult(ResultSet rs) throws SQLException {
        RoomDto room = new RoomDto();
        room.setId(rs.getLong("id"));
        room.setTitle(rs.getString("title"));
        return room;
    }

    public List<RoomDto> findLatestN(int topN) {
        try (Connection conn = dataSourceFactory.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.SELECT_LATEST_N)) {
            query.setInt(1, topN);
            try (ResultSet rs = query.executeQuery()) {
                List<RoomDto> findRooms = new ArrayList<>();
                while (rs.next()) {
                    findRooms.add(mapResult(rs));
                }
                return findRooms;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("방 리스트를 찾을 수 없습니다.");
        }
    }

    public int deleteById(long id) {
        try (Connection conn = dataSourceFactory.getConnection();
             PreparedStatement query = conn.prepareStatement(RoomDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("방을 삭제할 수 없습니다.");
        }
    }
}
