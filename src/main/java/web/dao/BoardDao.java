package web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dto.ChessGameDto;

public class BoardDao {
	private static final BoardDao boardDAO = new BoardDao();
	private static final String TABLE_NAME = "board";

	public static BoardDao getInstance() {
		return boardDAO;
	}

	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306";
		String database = "chessgame";
		String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
		String userName = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	public void addBoard(ChessGameDto chessGameDto) throws SQLException {
		String query = "INSERT INTO " + TABLE_NAME + " (board,turn,title) VALUES(?,?,?)";
		try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
			pstmt.setString(1, chessGameDto.getSymbol());
			pstmt.setString(2, chessGameDto.getTurn());
			pstmt.setString(3, chessGameDto.getTitle());
			pstmt.executeUpdate();
		}
	}

	public ChessGameDto findByTitle(String title) throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE title = ?";
		ResultSet rs;
		try (
			PreparedStatement pstmt = getConnection().prepareStatement(query);
		) {
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
			if (!rs.next())
				return null;
			return new ChessGameDto(
				rs.getString("title"),
				rs.getString("board"),
				rs.getString("turn"));
		}
	}
}
