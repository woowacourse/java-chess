package dao;

import dto.StatusRecordDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO test
public class StatusRecordDao {
	private static final StatusRecordDao STATUS_RECORD_WITH_ROOM_NAME_DAO;

	static {
		STATUS_RECORD_WITH_ROOM_NAME_DAO = new StatusRecordDao(ConnectionDao.getInstance());
	}

	private final ConnectionDao connectionDao;

	private StatusRecordDao(final ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public static StatusRecordDao getInstance() {
		return STATUS_RECORD_WITH_ROOM_NAME_DAO;
	}

	public List<StatusRecordDto> findStatusRecords() throws SQLException {
		final String query = "SELECT * "
				+ "FROM status_record "
				+ "ORDER BY room_name";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			return prepareStatusRecordDtos(preparedStatement);
		}
	}

	private List<StatusRecordDto> prepareStatusRecordDtos(final PreparedStatement preparedStatement)
			throws SQLException {
		try (final ResultSet resultSet = preparedStatement.executeQuery()) {
			return collectStatusRecordDtos(resultSet);
		}
	}

	private List<StatusRecordDto> collectStatusRecordDtos(final ResultSet resultSet)
			throws SQLException {
		final List<StatusRecordDto> statusRecordDtos = new ArrayList<>();
		while (resultSet.next()) {
			statusRecordDtos.add(
					new StatusRecordDto(resultSet.getInt("id"), resultSet.getString("record"),
							resultSet.getDate("game_date"), resultSet.getString("room_name")));
		}
		return statusRecordDtos;
	}

	public int addStatusRecord(final String record, final int roomId) throws SQLException {
		final String query = "INSERT INTO status_record(record, room_name) "
				+ "VALUES(?, (SELECT room_name FROM room WHERE room.id = ?))";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, record);
			preparedStatement.setInt(2, roomId);
			return preparedStatement.executeUpdate();
		}
	}
}
