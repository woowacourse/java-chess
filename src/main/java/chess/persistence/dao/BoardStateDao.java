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
            conn.setAutoCommit(false);
            query.setString(1, state.getType());
            query.setString(2, state.getCoordX());
            query.setString(3, state.getCoordY());
            query.executeUpdate();
            state.setId(getGeneratedKey(query));
            addPlayIn(state, conn);
            conn.commit();
            return state.getId();
        }
    }

    private long getGeneratedKey(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.getGeneratedKeys()) {
            rs.next();
            return rs.getLong(1);
        }
    }

    private void addPlayIn(BoardStateDto dto, Connection conn) throws SQLException {
        try (PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.INSERT_PLAY_IN)) {
            query.setLong(1, dto.getGameSessionId());
            query.setLong(2, dto.getId());
            query.executeUpdate();
        }
    }

    public Optional<BoardStateDto> findById(long id) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);
            return handleSingleResult(query);
        }
    }

    private Optional<BoardStateDto> handleSingleResult(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            if (!rs.next()) {
                return Optional.empty();
            }
            BoardStateDto state = mapResult(rs);
            return Optional.of(state);
        }
    }

    private BoardStateDto mapResult(ResultSet rs) throws SQLException {
        BoardStateDto state = new BoardStateDto();
        state.setId(rs.getLong("id"));
        state.setType(rs.getString("piece_type"));
        state.setCoordX(rs.getString("loc_x"));
        state.setCoordY(rs.getString("loc_y"));
        state.setGameSessionId(rs.getLong("session_id"));
        return state;
    }

    public List<BoardStateDto> findBySessionId(long sessionId) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement query = connection.prepareStatement(BoardStateDaoSql.SELECT_BY_SESSION_ID)) {
            query.setLong(1, sessionId);
            return handleMultipleResults(query);
        }
    }

    private List<BoardStateDto> handleMultipleResults(PreparedStatement query) throws SQLException {
        try (ResultSet rs = query.executeQuery()) {
            List<BoardStateDto> founds = new ArrayList<>();
            while (rs.next()) {
                founds.add(mapResult(rs));
            }
            return founds;
        }
    }

    public Optional<BoardStateDto> findByRoomIdAndCoordinate(long sessionId, String coordX, String coordY) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(BoardStateDaoSql.SELECT_BY_SESSION_ID_AND_COORDINATE)) {
            query.setLong(1, sessionId);
            query.setString(2, coordX);
            query.setString(3, coordY);
            return handleSingleResult(query);
        }
    }

    public int updateCoordById(BoardStateDto state) throws SQLException {
        try (Connection conn = dataSource.getConnection();
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
        private static final String INSERT = "INSERT INTO board_state(piece_type, loc_x, loc_y) VALUES(?, ?, ?)";
        private static final String INSERT_PLAY_IN = "INSERT INTO play_in(session_id, board_state_id) VALUES(?, ?)";
        private static final String SELECT_BY_ID = "SELECT bs.id, loc_x, loc_y, piece_type, bs.reg_date, session_id FROM board_state bs\n" +
            "JOIN play_in p\n" +
            "ON bs.id = p.board_state_id\n" +
            "WHERE bs.id=?";
        private static final String SELECT_BY_SESSION_ID = "SELECT bs.id, loc_x, loc_y, piece_type, bs.reg_date, session_id FROM board_state bs\n" +
            "JOIN play_in p\n" +
            "ON bs.id = p.board_state_id\n" +
            "WHERE session_id=?";
        private static final String UPDATE_COORD_BY_ID = "UPDATE board_state SET loc_x=?, loc_y=? WHERE id=?";
        private static final String DELETE_BY_ID = "DELETE FROM board_state WHERE id=?";
        private static final String SELECT_BY_SESSION_ID_AND_COORDINATE = "SELECT bs.id, loc_x, loc_y, piece_type, bs.reg_date, session_id FROM board_state bs\n" +
            "JOIN play_in p\n" +
            "ON bs.id = p.board_state_id\n" +
            "WHERE SESSION_id=? AND\n" +
            "loc_x=? AND\n" +
            "loc_y=?;";
    }
}
