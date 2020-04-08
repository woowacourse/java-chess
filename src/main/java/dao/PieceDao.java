package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
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

	public int addPiece(final String pieceTypeName, final String teamName, final String coordinateRepresentation,
						final Boolean canMoveTwoDistance, final int roomId) throws SQLException {
		final String query = "INSERT INTO piece(piece_type, team, coordinate, can_move_two_distance, room_id) "
				+ "VALUES(?, ?, ?, ?, ?)";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, pieceTypeName);
		preparedStatement.setString(2, teamName);
		preparedStatement.setString(3, coordinateRepresentation);
		preparedStatement.setString(4, canMoveTwoDistance.toString());
		preparedStatement.setInt(5, roomId);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}

	public List<dao.Piece> findPiecesByRoomId(final int roomId) throws SQLException {
		final String query = "SELECT * FROM piece WHERE room_id = ?";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomId);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<dao.Piece> pieces = new ArrayList<>();
		while (resultSet.next()) {
			pieces.add(new dao.Piece(resultSet.getInt("id"), resultSet.getString("piece_type"),
					resultSet.getString("team"), resultSet.getString("coordinate"),
					resultSet.getString("can_move_two_distance"), resultSet.getInt("room_id")));
		}

		resultSet.close();
		preparedStatement.close();
		closeConnection(connection);
		return pieces;
	}

	public int delete(final int roomId) throws SQLException {
		final String query = "DELETE FROM piece WHERE room_id = ?";
		final Connection connection = getConnection();
		final PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomId);
		final int resultNum = preparedStatement.executeUpdate();

		preparedStatement.close();
		closeConnection(connection);
		return resultNum;
	}
}
