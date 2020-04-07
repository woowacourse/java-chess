package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.chessboard.ChessBoard;
import chess.dto.ChessDTO;
import chess.domain.factory.BoardFactory;

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
		String query = "INSERT INTO board (whiteTurn, rows) VALUES (?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setBoolean(1, chessDto.isWhiteTurn());
		pstmt.setString(2, chessDto.getRawBoard());
		pstmt.executeUpdate();
	}

	public void remove(ChessDTO chessDTO) throws SQLException {
		String query = "DELETE FROM board WHERE id = (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, chessDTO.getId());
		pstmt.executeUpdate();
	}

	public ChessBoard find() throws SQLException {
		String query = "SELECT * FROM board";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();
		if (!resultSet.next()) {
			ChessBoard chessBoard = BoardFactory.createBoard();
			addBoard(new ChessDTO(chessBoard));
			return chessBoard;
		}
		int id = resultSet.getInt("id");
		String rows = resultSet.getString("rows");
		boolean isWhiteTurn = resultSet.getBoolean("whiteTurn");
		return BoardFactory.createBoard(new ChessDTO(id, rows, isWhiteTurn));
	}

	public void update(ChessDTO chessDTO) throws SQLException {
		String query = "UPDATE board SET rows = (?), whiteTurn = (?)"
			+ " WHERE id = (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, chessDTO.getRawBoard());
		pstmt.setBoolean(2, chessDTO.isWhiteTurn());
		pstmt.setInt(3, chessDTO.getId());
		pstmt.executeUpdate();
	}
}
