package chess.dao;

import chess.config.DbConnector;
import chess.dto.CommandDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandDao {
	private static final int DEFAULT_START_ROUND = 1;
	private final DbConnector dbConnector;

	private CommandDao(final DbConnector dbConnector) {
		this.dbConnector = dbConnector;
	}

	public static CommandDao from(final DbConnector dbConnector) {
		return new CommandDao(dbConnector);
	}

	public void add(final CommandDto commandDto) {
		String sql = "INSERT INTO command (origin, target, round, room_id) VALUES(?,?,?,?)";
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, commandDto.getOrigin());
			ps.setString(2, commandDto.getTarget());
			ps.setLong(3, commandDto.getRound());
			ps.setLong(4, commandDto.getRoomId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CommandDto> findByRoomId(final long roomId) {
		ArrayList<CommandDto> commandDtos = new ArrayList<>();

		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement ps = createPreparedStatementForFindByRoomId(conn, roomId);
		     ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				CommandDto commandDto = new CommandDto();
				commandDto.setOrigin(rs.getString("origin"));
				commandDto.setTarget(rs.getString("target"));
				commandDto.setRound(rs.getLong("round"));
				commandDtos.add(commandDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commandDtos;
	}

	private PreparedStatement createPreparedStatementForFindByRoomId(
			final Connection conn, final long roomId) throws SQLException {
		String sql = "SELECT * FROM command WHERE room_id = ? ORDER BY round";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, roomId);
		return ps;
	}

	public long findLatestRoundByRoomId(final long roomId) {
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement ps = createPreparedStatement(conn, roomId);
		     ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getLong("round");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DEFAULT_START_ROUND;
	}

	private PreparedStatement createPreparedStatement(final Connection conn, final long roomId) throws SQLException {
		String sql = "SELECT round FROM command WHERE room_id = ? ORDER BY round DESC LIMIT 1";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, roomId);
		return ps;
	}

	public void deleteAll() {
		String sql = "DELETE FROM command";
		try (Connection conn = dbConnector.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.executeUpdate();
			initializeIncrement(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initializeIncrement(final Connection conn) {
		String sql = "ALTER TABLE command ALTER COLUMN id RESTART WITH 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
