package domain.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.piece.Piece;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class BoardDao {
	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:3306"; // MySQL 서버 주소
		String database = "board"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
		String userName = "root"; //  MySQL 서버 아이디
		String password = "1234"; // MySQL 서버 비밀번호

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

	public void saveBoard(Board board) throws SQLException {
		Connection connection = getConnection();
		for (Rank rank : board.getRanks()) {
			savePiece(rank, connection);
		}
	}

	private void savePiece(Rank rank, Connection connection) throws SQLException {
		for (Piece piece : rank.getPieces()) {
			String query = "INSERT INTO board VALUES (?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			String position = piece.getPosition().getColumn().getColumnName() + String.valueOf(piece.getPosition().getRow());

			pstmt.setString(1, position);
			pstmt.setString(2, piece.showSymbol());
			pstmt.executeUpdate();
		}
	}
}
