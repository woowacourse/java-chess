package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO test
public class StatusRecordDao {
	private static final StatusRecordDao STATUS_RECORD_DAO;

	static {
		STATUS_RECORD_DAO = new StatusRecordDao(ConnectionDao.getInstance());
	}

	private final ConnectionDao connectionDao;

	private StatusRecordDao(final ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public static StatusRecordDao getInstance() {
		return STATUS_RECORD_DAO;
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
