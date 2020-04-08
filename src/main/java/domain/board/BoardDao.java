package domain.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.piece.Piece;
import domain.piece.PieceCreator;
import domain.piece.position.Position;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class BoardDao {
	private static final int FIRST_RANK_INDEX = 1;
	private static final int LAST_RANK_INDEX = 8;
	private static final int RANK_INDEX = 1;
	private static final String SERVER = "localhost:3306";// MySQL 서버 주소
	private static final String DATABASE = "board";// MySQL DATABASE 이름
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String USER_NAME = "root";//  MySQL 서버 아이디
	private static final String PASSWORD = "1234";// MySQL 서버 비밀번호

	public Connection getConnection() {
		Connection con = null;

		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(String.format(" !! JDBC Driver load 오류: %s", e.getMessage()));
		}

		// 드라이버 연결
		try {
			System.out.println("정상적으로 연결되었습니다.");
			con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION), USER_NAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println(String.format("연결 오류:%s", e.getMessage()));
		}
		return con;
	}

	public void clearBoardDb() throws SQLException {
		try (Connection connection = getConnection()) {
			String query = "DELETE FROM board";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
		}
	}

	public void saveBoard(Board board) throws SQLException {
		try (Connection connection = getConnection()) {
			for (Rank rank : board.getRanks()) {
				savePiece(rank, connection);
			}
		}
	}

	private void savePiece(Rank rank, Connection connection) throws SQLException {
		for (Piece piece : rank.getPieces()) {
			String query = "INSERT INTO board VALUES (?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			String position =
				piece.getPosition().getColumn().getColumnName() + String.valueOf(piece.getPosition().getRow());

			pstmt.setString(1, position);
			pstmt.setString(2, piece.showSymbol());
			pstmt.executeUpdate();
		}
	}

	public void clearTurn() throws SQLException {
		try (Connection connection = getConnection()) {
			String query = "DELETE FROM TURN";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.executeUpdate();
		}
	}

	public void saveTurn(Team turn) throws SQLException {
		try (Connection connection = getConnection()) {
			String query = "INSERT INTO turn VALUES (?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, turn.getName());
			pstmt.executeUpdate();
		}
	}

	public Board loadBoard() throws SQLException {
		List<Rank> ranks = new ArrayList<>();

		try (Connection connection = getConnection()) {
			for (int i = FIRST_RANK_INDEX; i <= LAST_RANK_INDEX; i++) {
				ranks.add(loadRank(i, connection));
			}
			return new Board(ranks);
		}
	}

	private Rank loadRank(int rankIndex, Connection connection) throws SQLException {
		List<Piece> pieces = new ArrayList<>();
		String query = "SELECT * FROM board";
		PreparedStatement pstmt = connection.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		char rankOfPiece;
		int pieceRank;

		while (rs.next()) {
			rankOfPiece = rs.getString("piece_position").charAt(RANK_INDEX);
			pieceRank = Integer.parseInt(String.valueOf(rankOfPiece));
			if (pieceRank == rankIndex) {
				pieces.add(createPiece(rs));
			}
		}
		return new Rank(pieces);
	}

	private Piece createPiece(ResultSet rs) throws SQLException {
		Position position = Position.of(rs.getString("piece_position"));
		return PieceCreator.of(rs.getString("symbol")).getPieceCreator().apply(position);
	}

	public Team loadTurn() throws SQLException {
		try (Connection connection = getConnection()) {
			String query = "SELECT * FROM turn";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return Team.of(rs.getString("game_turn"));
			}
		}
		throw new IllegalArgumentException("데이터 베이스에 저장된 Turn이 없습니다.");
	}
}
