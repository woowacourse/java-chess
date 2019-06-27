package chess.persistence.dao;

import chess.persistence.dto.TurnDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class TurnDao {
    private static class TurnDaoSql {
        private static final String INSERT = "INSERT INTO turn(room_id, current) VALUES(?, ?)";
        private static final String SELECT_BY_ID = "SELECT room_id, current FROM turn WHERE room_id=?";
        private static final String UPDATE_BY_ID = "UPDATE turn set current=? WHERE room_id=?";
        private static final String DELETE_BY_ID = "DELETE FROM turn WHERE room_id=?";
    }

    private DataSource dataSource;

    public TurnDao(DataSource ds) {
        this.dataSource = ds;
    }

    public long addTurn(TurnDto turn) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(TurnDaoSql.INSERT, Statement.RETURN_GENERATED_KEYS)) {
            query.setLong(1, turn.getRoomId());
            query.setString(2, turn.getCurrent());
            query.executeUpdate();
            try (ResultSet rs = query.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 추가할 수 없습니다.");
        }
    }

    public Optional<TurnDto> findById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(TurnDaoSql.SELECT_BY_ID)) {
            query.setLong(1, id);
            try (ResultSet rs = query.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                TurnDto turn = mapResult(rs);
                return Optional.of(turn);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 찾을 수 없습니다.");
        }
    }

    private TurnDto mapResult(ResultSet rs) throws SQLException {
        TurnDto turn = new TurnDto();
        turn.setRoomId(rs.getLong("room_id"));
        turn.setCurrent(rs.getString("current"));
        return turn;
    }

    public int updateTurnByRoomId(TurnDto turn) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(TurnDaoSql.UPDATE_BY_ID)) {
            query.setString(1, turn.getCurrent());
            query.setLong(2, turn.getRoomId());
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 업데이트할 수 없습니다.");
        }
    }

    public int deleteByRoomId(long roomId) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement query = conn.prepareStatement(TurnDaoSql.DELETE_BY_ID)) {
            query.setLong(1, roomId);
            return query.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("턴을 삭제할 수 없습니다.");
        }
    }
}
