package chess.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

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
	private static final String CONNECTION_FORMAT = String.format("jdbc:mysql://%s/%s%s", ADDRESS, NAME, OPTION);
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
			con = DriverManager.getConnection(CONNECTION_FORMAT, ID, PASSWORD);
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

	public Optional<Piece> findPieceBy(String position) throws SQLException {
		Piece piece;
		String[] selected = executeSelectQuery(position);

		if (Objects.isNull(selected[0])) {
			return Optional.empty();
		}

		piece = WhitePieceMapper.mappingBy(selected[0], selected[1]);
		if (Objects.isNull(piece)) {
			piece = BlackPieceMapper.mappingBy(selected[0], selected[1]);
		}

		return Optional.ofNullable(piece);
	}

	public Optional<Piece> findWhitePieceBy(String position) throws SQLException {
		Piece piece;
		String[] selected = executeSelectQuery(position);

		if (Objects.isNull(selected[0])) {
			return Optional.empty();
		}

		piece = WhitePieceMapper.mappingBy(selected[0], selected[1]);

		return Optional.ofNullable(piece);
	}

	public Optional<Piece> findBlackPieceBy(String position) throws SQLException {
		Piece piece;
		String[] selected = executeSelectQuery(position);

		if (Objects.isNull(selected[0])) {
			return Optional.empty();
		}

		piece = BlackPieceMapper.mappingBy(selected[0], selected[1]);

		return Optional.ofNullable(piece);
	}

	public void updateBoard(String position, Piece piece) throws SQLException {
		String query = "UPDATE board SET pieceSymbol = ?, pieceState = ? WHERE position = ?";
		if (Objects.isNull(piece)) {
			executeUpdate(query, null, null, position);
			return;
		}
		executeUpdate(query, piece.getSymbol(), piece.getState().toString(), position);
	}

	public String[] executeSelectQuery(String position) throws SQLException {
		String[] selectPiece = new String[2];
		String query = "SELECT * FROM board WHERE position = ?";
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setString(1, position);
			ResultSet rs = pstmt.executeQuery();
			if (!rs.next()) {
				selectPiece[0] = null;
				selectPiece[1] = null;
				return selectPiece;
			}

			selectPiece[0] = rs.getString("pieceSymbol");
			selectPiece[1] = rs.getString("pieceState");
			rs.close();

			return selectPiece;
		}
	}

	public void executeUpdate(String query, String... args) throws SQLException {
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			for (int i = 1; i <= args.length; i++) {
				pstmt.setString(i, args[i - 1]);
			}
			pstmt.executeUpdate();
		}
	}

}
