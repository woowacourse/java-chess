package dao;

import dao.exceptions.DaoNoneSelectedException;
import dto.AnnouncementDto;

import java.sql.*;

public class AnnouncementDao {
	public Connection getConnection() {
		Connection connection = null;
		final String server = "localhost:13306"; // MySQL 서버 주소
		final String database = "woowachess"; // MySQL DATABASE 이름
		final String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
		final String userName = "root"; //  MySQL 서버 아이디
		final String password = "root"; // MySQL 서버 비밀번호

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

	public int addAnnouncement(final String message, final int roomId) throws SQLException {
		String query = "INSERT INTO announcement(message, room_id) VALUES(?, ?)";

		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, message);
		preparedStatement.setInt(2, roomId);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}

	public AnnouncementDto findAnnouncementByRoomId(final int roomId) throws SQLException {
		final String query = "SELECT * FROM announcement WHERE room_id = ?";

		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomId);

		final ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			throw new DaoNoneSelectedException();
		}
		final AnnouncementDto announcement = new AnnouncementDto(resultSet.getInt("id"),
				resultSet.getString("message"), resultSet.getInt("room_id"));

		resultSet.close();
		preparedStatement.close();
		closeConnection(connection);
		return announcement;
	}

	public int setAnnouncementByRoomId(final int roomId, final String message) throws SQLException {
		final String query = "UPDATE announcement SET message=? WHERE room_id=?";

		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, message);
		preparedStatement.setInt(2, roomId);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}
}
