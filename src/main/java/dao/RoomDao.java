package dao;

import dao.exceptions.DaoNoneSelectedException;
import dto.RoomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
	private static final RoomDao ROOM_DAO;

	static {
		ROOM_DAO = new RoomDao(ConnectionDao.getInstance());
	}

	private final ConnectionDao connectionDao;

	private RoomDao(final ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public static RoomDao getInstance() {
		return ROOM_DAO;
	}

	public int addRoomByRoomName(final String roomName) throws SQLException {
		final String query = "INSERT INTO room (room_name) VALUES (?)";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, roomName);
			return preparedStatement.executeUpdate();
		}
	}

	public RoomDto findRoomByRoomName(final String roomName) throws SQLException {
		final String query = "SELECT * FROM room WHERE room_name = ?";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, roomName);
			return prepareRoomDto(preparedStatement);
		}
	}

	private RoomDto prepareRoomDto(final PreparedStatement preparedStatement) throws SQLException {
		try (final ResultSet resultSet = preparedStatement.executeQuery()) {
			validateHasResult(resultSet);
			return new RoomDto(resultSet.getInt("id"),
					resultSet.getString("room_name"));
		}
	}

	private void validateHasResult(final ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			throw new DaoNoneSelectedException();
		}
	}

	public List<RoomDto> findAllRooms() throws SQLException {
		final String query = "SELECT * FROM room";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			return prepareRoomDtos(preparedStatement);
		}
	}

	private List<RoomDto> prepareRoomDtos(final PreparedStatement preparedStatement) throws SQLException {
		try (final ResultSet resultSet = preparedStatement.executeQuery()) {
			return collectRoomDtos(resultSet);
		}
	}

	private List<RoomDto> collectRoomDtos(final ResultSet resultSet) throws SQLException {
		final List<RoomDto> roomDtos = new ArrayList<>();
		while (resultSet.next()) {
			roomDtos.add(new RoomDto(resultSet.getInt("id"),
					resultSet.getString("room_name")));
		}
		return roomDtos;
	}

	public int deleteRoomByRoomName(final String roomName) throws SQLException {
		final String query = "DELETE FROM room WHERE room_name = ?";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, roomName);
			return preparedStatement.executeUpdate();
		}
	}
}
