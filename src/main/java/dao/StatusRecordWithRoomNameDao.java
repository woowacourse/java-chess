package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO test
public class StatusRecordWithRoomNameDao {
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

	public List<StatusRecordWithRoomName> findStatusRecordWithRoomName() throws SQLException {
		final String query = "SELECT status_record.record, status_record.game_date, room.room_name "
				+ "FROM status_record JOIN room ON status_record.room_id = room.id "
				+ "ORDER BY room.room_name";

		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		final ResultSet resultSet = preparedStatement.executeQuery();
		final List<StatusRecordWithRoomName> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(new StatusRecordWithRoomName(resultSet.getString("status_record.record"),
					resultSet.getDate("status_record.game_date"), resultSet.getString("room.room_name")));
		}
		return list;
	}
}
