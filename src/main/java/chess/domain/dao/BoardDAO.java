package chess.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

public class BoardDAO {
	private final String gameId;

	public BoardDAO(String gameId) {
		this.gameId = gameId;
	}

	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "db_name"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
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

	public void addPiece(Piece piece) {
		String query = "INSERT INTO board VALUES (?, ?, ?)";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			pstmt.setString(2, piece.getPosition().getName());
			pstmt.setString(3, piece.getSymbol());
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public Piece findPieceBy(Position position) {
		String query = "SELECT * FROM board WHERE game_id = ? AND position = ?";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, gameId);
			pstmt.setString(2, position.getName());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Piece piece = PieceFactory.of(rs.getString("symbol")).create(position);
			pstmt.close();
			closeConnection(con);

			return piece;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public void truncate() {
		String query = "TRUNCATE board";
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(con);
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}