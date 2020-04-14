package dao;

import dao.exceptions.DaoNoneSelectedException;
import dto.StateDto;

import java.sql.*;

public class StateDao {
	private static final StateDao STATE_DAO;

	static {
		STATE_DAO = new StateDao(ConnectionDao.getInstance());
	}

	private final ConnectionDao connectionDao;

	private StateDao(final ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public static StateDao getInstance() {
		return STATE_DAO;
	}

	public int addState(final String state, final int roomId) throws SQLException {
		final String query = "INSERT INTO state (state, room_id) "
				+ "VALUES (?, ?)";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, roomId);
			return preparedStatement.executeUpdate();
		}
	}

	private StateDto prepareStateDto(final PreparedStatement preparedStatement) throws SQLException {
		try (final ResultSet resultSet = preparedStatement.executeQuery()) {
			validateHasResult(resultSet);

			return new StateDto(resultSet.getInt("id"), resultSet.getString("state"),
					resultSet.getInt("room_id"));
		}
	}

	private void validateHasResult(final ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			throw new DaoNoneSelectedException();
		}
	}

	// TODO test
	public StateDto findStateByRoomId(final int roomId) throws SQLException {
		final String query = "SELECT * FROM state WHERE room_id=?";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, roomId);
			return prepareStateDto(preparedStatement);
		}
	}

	public int setStateByRoomId(final int roomId, final String state) throws SQLException {
		final String query = "UPDATE state SET state=? WHERE room_id=?";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, roomId);
			return preparedStatement.executeUpdate();
		}
	}
}
