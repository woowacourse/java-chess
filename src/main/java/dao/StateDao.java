package dao;

import dao.exceptions.DaoNoneSelectedException;

import java.sql.*;

public class StateDao {
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

	public int addState(final String state, final String roomName) throws SQLException {
		final String query = "INSERT INTO state (state, room_id) "
				+ "VALUES (?, (SELECT id FROM room WHERE room_name=?))";
		final PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, state);
		preparedStatement.setString(2, roomName);
		return preparedStatement.executeUpdate();
	}

	public State findStateByRoomName(final String roomName) throws SQLException {
		final String query = "SELECT * FROM state WHERE room_id="
				+ "(SELECT id FROM room WHERE room_name=?)";
		final PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, roomName);
		final ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			throw new DaoNoneSelectedException();
		}
		return new State(resultSet.getInt("id"), resultSet.getString("state"), resultSet.getInt("room_id"));
	}

	public int setState(final int id, final String state) throws SQLException {
		final String query = "UPDATE state SET state=? WHERE id=?";
		final PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setString(1, state);
		preparedStatement.setInt(2, id);
		return preparedStatement.executeUpdate();
	}
}
