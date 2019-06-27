package chess.persistence.dao;

import chess.persistence.dto.BoardStateDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardStateDao {
    private static class BoardStateDaoSql {
        private static final String INSERT = "INSERT INTO board_state(type, loc_x, loc_y, room_id) VALUES(?, ?, ?, ?)";
        private static final String SELECT_BY_ID = "SELECT id, type, loc_x, loc_y, room_id FROM board_state WHERE id=?";
        private static final String SELECT_BY_ROOM_ID = "SELECT id, type, loc_x, loc_y, room_id FROM board_state WHERE room_id=?";
        private static final String UPDATE_COORD_BY_ID = "UPDATE board_state SET loc_x=?, loc_y=? WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM board_state WHERE id=?";
    }

    private DataSource dataSource;

    public BoardStateDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addState(BoardStateDto state) {
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
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드 상태를 추가할 수 없습니다.");
        }
    }

    public Optional<BoardStateDto> findById(long id) {
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
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드 상태를 찾을 수 없습니다.");
        }
    }

    public List<BoardStateDto> findByRoomId(long roomId) {
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
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드 상태를 찾을 수 없습니다.");
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

    public int updateCoordById(BoardStateDto state) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.UPDATE_COORD_BY_ID)) {
            query.setString(1, state.getCoordX());
            query.setString(2, state.getCoordY());
            query.setLong(3, state.getId());
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드 상태를 업데이트 할 수 없습니다.");
        }
    }

    public int deleteById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.DELETE_BY_ID)) {
            query.setLong(1, id);
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("보드 상태를 지울 수 없습니다.");
        }
    }
}
