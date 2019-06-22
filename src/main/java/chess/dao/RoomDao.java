package chess.dao;

import chess.config.DbConnector;
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
		     PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<RoomDto> findById(final long id) {
		RoomDto roomDto = null;
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement pstmt = createPreparedStatementForFindById(conn, id);
		     ResultSet rs = pstmt.executeQuery()) {
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
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		return pstmt;
	}

	public List<RoomDto> findAllByStatus(final boolean status) {
		List<RoomDto> roomDtos = new ArrayList<>();

		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement pstmt = createPreparedStatementForFindByStatus(conn, status);
		     ResultSet rs = pstmt.executeQuery()) {
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

	private PreparedStatement createPreparedStatementForFindByStatus(
			final Connection conn, final boolean status) throws SQLException {
		String sql = "SELECT * FROM room WHERE status = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setBoolean(1, status);
		return pstmt;
	}

	public void updateStatus(final long id, final String winner) {
		String sql = "UPDATE room SET status = TRUE, winner = ? WHERE id = ?";
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, winner);
			pstmt.setLong(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Long> getLatestId() {
		String sql = "SELECT id FROM room ORDER BY id DESC LIMIT 1";
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(sql);
		     ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return Optional.of(rs.getLong("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void deleteAll() {
		String sql = "DELETE FROM room";
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();
			initializeIncrement(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void initializeIncrement(final Connection conn)  {
		String sql = "ALTER TABLE room ALTER COLUMN id RESTART WITH 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
