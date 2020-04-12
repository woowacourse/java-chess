package chess.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import chess.db.BlackPieceRowMapper;
import chess.db.PieceRowMapper;
import chess.db.RowMapper;
import chess.db.WhitePieceRowMapper;
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
	private static final String SELECT_FROM_BOARD_WHERE_POSITION = "SELECT * FROM board WHERE position = ?";

	public Connection getConnection() {
		Connection con = null;

		// 드라이버 로딩
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println(String.format(" !! JDBC Driver load 오류: %s", e.getMessage()));
		}

		// 드라이버 연결
		try {
			con = DriverManager.getConnection(CONNECTION_FORMAT, ID, PASSWORD);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
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
		executeUpdate("INSERT INTO board VALUES (?, ?, ?)"
			, position.getPosition(), piece.getSymbol(), piece.getState().toString());
	}

	public Optional<Piece> findPieceBy(String position) throws SQLException {
		return Optional.ofNullable(executeQuery(SELECT_FROM_BOARD_WHERE_POSITION, new PieceRowMapper(), position));
	}

	public Optional<Piece> findWhitePieceBy(String position) throws SQLException {
		return Optional.ofNullable(executeQuery(SELECT_FROM_BOARD_WHERE_POSITION, new WhitePieceRowMapper(), position));
	}

	public Optional<Piece> findBlackPieceBy(String position) throws SQLException {
		return Optional.ofNullable(executeQuery(SELECT_FROM_BOARD_WHERE_POSITION, new BlackPieceRowMapper(), position));
	}

	public void updateBoard(String position, Piece piece) throws SQLException {
		String query = "UPDATE board SET pieceSymbol = ?, pieceState = ? WHERE position = ?";
		if (Objects.isNull(piece)) {
			executeUpdate(query, null, null, position);
			return;
		}
		executeUpdate(query, piece.getSymbol(), piece.getState().toString(), position);
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

	public <T> T executeQuery(String query, RowMapper<T> rowMapper, String... args) throws SQLException {
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			for (int i = 1; i <= args.length; i++) {
				pstmt.setString(i, args[i - 1]);
			}
			return rowMapper.mapRow(pstmt.executeQuery());
		}
	}
}
