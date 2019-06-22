package chess.dao;

import chess.config.DbConnector;
import chess.domain.Piece;
import chess.dto.RoomDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {

    private final DbConnector dbConnector;

    private RoomDao(final DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public static RoomDao from(final DbConnector dbConnector) {
        return new RoomDao(dbConnector);
    }


    public void add() {
        String sql = "INSERT INTO room () VALUES()";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Optional<RoomDto> findById(final long id) {
        RoomDto roomDto = null;
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatementForFindById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                roomDto = new RoomDto();
                roomDto.setId(rs.getLong("id"));
                roomDto.setStatus(rs.getBoolean("status"));
                roomDto.setWinner(rs.getString("winner"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(roomDto);
    }

    private PreparedStatement createPreparedStatementForFindById(final Connection conn, final long id) throws SQLException {
        String sql = "SELECT * FROM room WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public List<RoomDto> findAllByStatus(final boolean status) {
        List<RoomDto> roomDtos = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = createPreparedStatementForFindByStatus(conn, status);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RoomDto roomDto = new RoomDto();
                roomDto.setId(rs.getLong("id"));
                roomDto.setStatus(rs.getBoolean("status"));
                roomDto.setWinner(rs.getString("winner"));
                roomDtos.add(roomDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomDtos;
    }

    private PreparedStatement createPreparedStatementForFindByStatus(final Connection conn, final boolean status) throws SQLException{
        String sql = "SELECT * FROM room WHERE status = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, status);
        return ps;
    }

    public void updateStatus(final long id, final String winner) {
        String sql = "UPDATE room SET status = TRUE, winner = ? WHERE id = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, winner);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Long> getLatestId() {
        String sql = "SELECT id FROM room ORDER BY id DESC LIMIT 1";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return Optional.of(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
