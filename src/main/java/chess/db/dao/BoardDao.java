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
	private static final String ADDRESS = "localhost:13306";
	private static final String NAME = "chess";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String ID = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	public Connection getConnection() {
		Connection con = null;

		// 드라이버 로딩
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println(String.format(" !! JDBC Driver load 오류: %s", e.getMessage()));
			e.printStackTrace();
		}

		// 드라이버 연결
		try {
			con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s%s", ADDRESS, NAME, OPTION)
				, ID, PASSWORD);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
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
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, position.getPosition());
			if (Objects.isNull(piece)) {
				pstmt.setString(2, null);
				pstmt.setString(3, null);
			} else {
				pstmt.setString(2, piece.getSymbol());
				pstmt.setString(3, piece.getState().toString());
			}
			pstmt.executeUpdate();
		}
	}

	public Piece findPieceBy(String position) throws SQLException {
		Piece piece;
		String query = "SELECT * FROM board WHERE position = ?";
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, position);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next())
				return null;

			if (Objects.isNull(rs.getString("pieceSymbol"))) {
				return null;
			}

			piece = WhitePieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
			if (Objects.isNull(piece)) {
				piece = BlackPieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
			}
		}
		return piece;
	}

	public Piece findWhitePieceBy(String position) throws SQLException {
		Piece piece;
		String query = "SELECT * FROM board WHERE position = ?";
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, position);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next())
				return null;

			if (Objects.isNull(rs.getString("pieceSymbol"))) {
				return null;
			}

			piece = WhitePieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
		}
		return piece;
	}

	public Piece findBlackPieceBy(String position) throws SQLException {
		Piece piece;
		String query = "SELECT * FROM board WHERE position = ?";
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, position);
			ResultSet rs = pstmt.executeQuery();

			if (!rs.next())
				return null;

			if (Objects.isNull(rs.getString("pieceSymbol"))) {
				return null;
			}

			piece = BlackPieceMapper.mappingBy(rs.getString("pieceSymbol"), rs.getString("pieceState"));
		}
		return piece;
	}

	public void updateBoard(String position, Piece piece) throws SQLException {
		String query = "UPDATE board SET pieceSymbol = ?, pieceState = ? WHERE position = ?";
		try (
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query)) {
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
		}
	}
}
