package chess.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import chess.db.BlackPieceMapper;
import chess.db.WhitePieceMapper;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

/**
 *    Board Dao 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class BoardDao {
	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "chess"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
		String boardName = "root"; // MySQL 서버 아이디
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
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, boardName, password);
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

	public void createBoard(Board board) {
		board.getBoard().forEach((key, value) -> {
			try {
				addPieceTo(key, value);
			} catch (SQLException e) {
				System.err.println("add 오류:" + e.getMessage());
			}
		});
	}

	private void addPieceTo(Position position, Piece piece) throws SQLException {
		String query = "INSERT INTO board VALUES (?, ?, ?)";
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setString(1, position.getPosition());
		if (Objects.isNull(piece)) {
			pstmt.setString(2, null);
			pstmt.setString(3, null);
		} else {
			pstmt.setString(2, piece.getSymbol());
			pstmt.setString(3, piece.getState().toString());
		}
		pstmt.executeUpdate();
		pstmt.close();
		closeConnection(con);
	}

	public Piece findPieceBy(String position) throws SQLException {
		String query = "SELECT * FROM board WHERE position = ?";
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, position);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next())
			return null;

		if (Objects.isNull(rs.getString("pieceSymbol"))) {
			return null;
		}

		Piece piece = WhitePieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
		if (Objects.isNull(piece)) {
			piece = BlackPieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
		}
		pstmt.close();
		closeConnection(con);
		return piece;
	}

	public Piece findWhitePieceBy(String position) throws SQLException {
		String query = "SELECT * FROM board WHERE position = ?";
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, position);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next())
			return null;

		if (Objects.isNull(rs.getString("pieceSymbol"))) {
			return null;
		}

		Piece piece = WhitePieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
		pstmt.close();
		closeConnection(con);
		return piece;
	}

	public Piece findBlackPieceBy(String position) throws SQLException {
		String query = "SELECT * FROM board WHERE position = ?";
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, position);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next())
			return null;

		if (Objects.isNull(rs.getString("pieceSymbol"))) {
			return null;
		}

		Piece piece = BlackPieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
		pstmt.close();
		closeConnection(con);
		return piece;
	}

	public void updateBoard(String position, Piece piece) throws SQLException {
		String query = "UPDATE board SET pieceSymbol = ?, pieceState = ? WHERE position = ?";
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(query);

		pstmt.setString(3, position);
		if (Objects.isNull(piece)) {
			pstmt.setString(1, null);
			pstmt.setString(2, null);
			pstmt.executeUpdate();
			return;
		}
		pstmt.setString(1, piece.getSymbol());
		pstmt.setString(2, piece.getState().toString());
		pstmt.executeUpdate();
		pstmt.close();
		closeConnection(con);
	}
}
