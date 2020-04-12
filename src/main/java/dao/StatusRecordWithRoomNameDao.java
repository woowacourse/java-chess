package dao;

import dto.StatusRecordWithRoomNameDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO test
public class StatusRecordWithRoomNameDao {
	private static final StatusRecordWithRoomNameDao STATUS_RECORD_WITH_ROOM_NAME_DAO;

	static {
		STATUS_RECORD_WITH_ROOM_NAME_DAO = new StatusRecordWithRoomNameDao(ConnectionDao.getInstance());
	}

	private final ConnectionDao connectionDao;

	private StatusRecordWithRoomNameDao(final ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public static StatusRecordWithRoomNameDao getInstance() {
		return STATUS_RECORD_WITH_ROOM_NAME_DAO;
	}

	public List<StatusRecordWithRoomNameDto> findStatusRecordWithRoomName() throws SQLException {
		final String query = "SELECT record, game_date, room_name "
				+ "FROM status_record "
				+ "ORDER BY room_name";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			return prepareStatusRecordsWithRoomNameDto(preparedStatement);
		}
	}

	private List<StatusRecordWithRoomNameDto> prepareStatusRecordsWithRoomNameDto
			(final PreparedStatement preparedStatement) throws SQLException {
		try (final ResultSet resultSet = preparedStatement.executeQuery()) {
			return collectStatusRecordsWithRoomNameDto(resultSet);
		}
	}

	private List<StatusRecordWithRoomNameDto> collectStatusRecordsWithRoomNameDto(final ResultSet resultSet)
			throws SQLException {
		final List<StatusRecordWithRoomNameDto> statusRecordsWithRoomNameDto = new ArrayList<>();
		while (resultSet.next()) {
			statusRecordsWithRoomNameDto.add(
					new StatusRecordWithRoomNameDto(resultSet.getString("record"),
							resultSet.getDate("game_date"), resultSet.getString("room_name")));
		}
		return statusRecordsWithRoomNameDto;
	}
}
