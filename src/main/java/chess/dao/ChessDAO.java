package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import chess.dto.ChessDTO;

public class ChessDAO {
	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:3306"; // MySQL 서버 주소
		String database = "chess"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
		String userName = "woowa2"; //  MySQL 서버 아이디
		String password = "test123"; // MySQL 서버 비밀번호

		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}

		// 드라이버 연결
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	// 드라이버 연결해제
	public void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("con 오류:" + e.getMessage());
		}
	}

	public void addBoard(ChessDTO chessDto) throws SQLException {
		String query = "INSERT INTO board (rows) VALUES (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, chessDto.getRows());
		pstmt.executeUpdate();
	}

	public int count() throws SQLException {
		String query = "SELECT COUNT(*) count FROM board";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();
		return Integer.parseInt(resultSet.getString("count"));
	}

	public ChessDTO find() throws SQLException {
		String query = "SELECT * FROM board";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();
		if (!resultSet.next()) {
			return null;
		}
		String rows = resultSet.getString("rows");
		return new ChessDTO(rows);
	}
}
