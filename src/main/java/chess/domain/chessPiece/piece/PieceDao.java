package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceDao {
	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "chess"; // MySQL DATABASE 이름
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

	public void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("con 오류:" + e.getMessage());
		}
	}

	public void addPiece(Piece piece) throws SQLException {
		String query = "INSERT INTO pieces(name,file,rank,team) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);

		pstmt.setString(1, piece.getPieceName());
		pstmt.setString(2, piece.getFile());
		pstmt.setString(3, piece.getRank());
		pstmt.setString(4, piece.getTeamStrategy().toString());

		pstmt.executeUpdate();
		pstmt.close();
	}

	public void deletePiece(Position targetPosition) throws SQLException {
		String query = "delete from pieces where file=? and rank=?";
		PreparedStatement pstmt = getConnection().prepareStatement(query);

		pstmt.setString(1, targetPosition.getFile());
		pstmt.setString(2, targetPosition.getRank());

		pstmt.executeUpdate();
		pstmt.close();
	}

	public void updatePiece(Position sourcePosition, Position targetPosition) throws SQLException {
		String query = "UPDATE pieces SET file = ?, rank = ? WHERE file=? AND rank=?";
		PreparedStatement pstmt = getConnection().prepareStatement(query);

		pstmt.setString(1, targetPosition.getFile());
		pstmt.setString(2, targetPosition.getRank());
		pstmt.setString(3, sourcePosition.getFile());
		pstmt.setString(4, sourcePosition.getRank());

		pstmt.executeUpdate();
		pstmt.close();
	}

	public void deleteAll() throws SQLException {
		String query = "delete from pieces";

		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.executeUpdate();
		pstmt.close();
	}

	public List<Map<String, Object>> readPieces() throws SQLException {
		String query = "SELECT * from pieces";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();

		List<Map<String, Object>> pieces = new ArrayList<>();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				map.put(metaData.getColumnName(i), rs.getObject(i));
			}
			pieces.add(map);
		}

		pstmt.close();
		return pieces;
	}
}
