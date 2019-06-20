package chess.persistence.dao;

import chess.persistence.dto.BoardStateDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardStateDao {

    private DataSource dataSource;

    public BoardStateDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addState(BoardStateDto state) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, state.getType());
            query.setString(2, state.getCoordX());
            query.setString(3, state.getCoordY());
            query.setLong(4, state.getRoomId());
            query.executeUpdate();
            try (ResultSet rs = query.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        }
    }

    public Optional<BoardStateDto> findById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);
            try (ResultSet rs = query.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                BoardStateDto state = mapResult(rs);
                return Optional.of(state);
            }
        }
    }

    private BoardStateDto mapResult(ResultSet rs) throws SQLException {
        BoardStateDto state = new BoardStateDto();
        state.setId(rs.getLong("id"));
        state.setType(rs.getString("type"));
        state.setCoordX(rs.getString("loc_x"));
        state.setCoordY(rs.getString("loc_y"));
        state.setRoomId(rs.getLong("room_id"));
        return state;
    }

    public List<BoardStateDto> findByRoomId(long roomId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement query = connection.prepareStatement(BoardStateDaoSql.SELECT_BY_ROOM_ID)) {
            query.setLong(1, roomId);
            try (ResultSet rs = query.executeQuery()) {
                List<BoardStateDto> founds = new ArrayList<>();
                while (rs.next()) {
                    founds.add(mapResult(rs));
                }
                return founds;
            }
        }
    }

    public int updateCoordById(BoardStateDto state) throws SQLException {
        try(Connection conn = dataSource.getConnection();
            PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.UPDATE_COORD_BY_ID)) {
            query.setString(1, state.getCoordX());
            query.setString(2, state.getCoordY());
            query.setLong(3, state.getId());
            return query.executeUpdate();
        }
    }

    public int deleteById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        }
    }

    private static class BoardStateDaoSql {
        private static final String INSERT = "INSERT INTO board_state(type, loc_x, loc_y, room_id) VALUES(?, ?, ?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, type, loc_x, loc_y, room_id FROM board_state WHERE id=?";
        private static final String SELECT_BY_ROOM_ID = "SELECT id, type, loc_x, loc_y, room_id FROM board_state WHERE room_id=?";
        private static final String UPDATE_COORD_BY_ID = "UPDATE board_state SET loc_x=?, loc_y=? WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM board_state WHERE id=?";
    }
}
