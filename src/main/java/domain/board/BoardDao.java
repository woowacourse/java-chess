package domain.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
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

	public void clearBoardDb() throws SQLException {
		String query = "DELETE FROM board";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.executeUpdate();
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
			String position =
				piece.getPosition().getColumn().getColumnName() + String.valueOf(piece.getPosition().getRow());

			pstmt.setString(1, position);
			pstmt.setString(2, piece.showSymbol());
			pstmt.executeUpdate();
		}
	}

	public void clearTurn() throws SQLException {
		String query = "DELETE FROM TURN";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}

	public void saveTurn(Team turn) throws SQLException {
		String query = "INSERT INTO turn VALUES (?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, turn.getName());
		pstmt.executeUpdate();
	}

	public Board loadBoard() throws SQLException {
		List<Rank> ranks = new ArrayList<>();
		Connection connection = getConnection();

		for (int i = FIRST_RANK_INDEX; i <= LAST_RANK_INDEX; i++) {
			ranks.add(loadRank(i, connection));
		}
		return new Board(ranks);
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

		switch (rs.getString("symbol")) {
			case "p" : return new Pawn(position, Team.WHITE);
			case "P" : return new Pawn(position, Team.BLACK);
			case "r" : return new Rook(position, Team.WHITE);
			case "R" : return new Rook(position, Team.BLACK);
			case "n" : return new Knight(position, Team.WHITE);
			case "N" : return new Knight(position, Team.BLACK);
			case "b" : return new Bishop(position, Team.WHITE);
			case "B" : return new Bishop(position, Team.BLACK);
			case "k" : return new King(position, Team.WHITE);
			case "K" : return new King(position, Team.BLACK);
			case "q" : return new Queen(position, Team.WHITE);
			case "Q" : return new Queen(position, Team.BLACK);
			default:
				throw new IllegalStateException("Unexpected value: " + rs.getString("symbol"));
		}
	}
}
