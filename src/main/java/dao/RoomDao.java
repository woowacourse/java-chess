package dao;

import dao.exceptions.DaoNoneSelectedException;
import dto.RoomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
	public Connection getConnection() {
		Connection connection = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "woowachess"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
		String userName = "root"; //  MySQL 서버 아이디
		String password = "root"; // MySQL 서버 비밀번호

		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}

		// 드라이버 연결
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + server + "/" + database + option, userName, password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return connection;
	}

	// 드라이버 연결해제
	public void closeConnection(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.err.println("con 오류:" + e.getMessage());
		}
	}

	public int addRoomByRoomName(final String roomName) throws SQLException {
		final String query = "INSERT INTO room (room_name) VALUES (?)";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, roomName);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}

	public RoomDto findRoomByRoomName(final String roomName) throws SQLException {
		final String query = "SELECT * FROM room "
				+ "WHERE room_name = ?";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, roomName);
		final ResultSet resultSet = preparedStatement.executeQuery();

		if (!resultSet.next()) {
			throw new DaoNoneSelectedException();
		}
		final RoomDto roomDto = new RoomDto(resultSet.getInt("id"),
				resultSet.getString("room_name"));

		resultSet.close();
		preparedStatement.close();
		closeConnection(connection);
		return roomDto;
	}

	// TODO test
	public RoomDto findRoomByRoomId(final int roomId) throws SQLException {
		final String query = "SELECT * FROM room "
				+ "WHERE id = ?";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomId);
		final ResultSet resultSet = preparedStatement.executeQuery();

		if (!resultSet.next()) {
			throw new DaoNoneSelectedException();
		}
		final RoomDto roomDto = new RoomDto(resultSet.getInt("id"),
				resultSet.getString("room_name"));

		resultSet.close();
		preparedStatement.close();
		closeConnection(connection);
		return roomDto;
	}


	public List<RoomDto> findAllRooms() throws SQLException {
		final String query = "SELECT * FROM room";
		final PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		final ResultSet resultSet = preparedStatement.executeQuery();
		final List<RoomDto> roomDtos = new ArrayList<>();
		while (resultSet.next()) {
			roomDtos.add(new RoomDto(resultSet.getInt("id"),
					resultSet.getString("room_name")));
		}
		return roomDtos;
	}

	public int deleteRoomByRoomName(final String roomName) throws SQLException {
		final String query = "DELETE FROM room "
				+ "WHERE room_name = ?";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, roomName);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}
}
