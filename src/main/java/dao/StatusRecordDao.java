package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// TODO test
public class StatusRecordDao {
	private static final StatusRecordDao STATUS_RECORD_DAO;

	static {
		STATUS_RECORD_DAO = new StatusRecordDao();
	}

	private StatusRecordDao() {
	}

	public static StatusRecordDao getInstance() {
		return STATUS_RECORD_DAO;
	}

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

	public int addStatusRecord(final String record, final int roomId) throws SQLException {
		final String query = "INSERT INTO status_record(record, room_id) VALUES(?, ?)";

		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, record);
		preparedStatement.setInt(2, roomId);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}

}
