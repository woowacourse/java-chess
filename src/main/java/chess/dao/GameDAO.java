package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMapper;
import chess.domain.piece.Pieces;
import chess.util.JDBCConnector;

public class GameDAO {
	private static final GameDAO GAME_DAO = new GameDAO();

	public static GameDAO getInstance() {
		return GAME_DAO;
	}

	public void removeAllPiecesById(int roomId) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "DELETE FROM board WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public void addAllPiecesById(int roomId, Pieces pieces) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		for (Position position : pieces.getPieces().keySet()) {
			Piece piece = pieces.getPieceByPosition(position);

			String query = "INSERT INTO board(room_id, piece_name, piece_position, piece_color)"
				+ " VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, roomId);
			pstmt.setString(2, piece.getSymbol());
			pstmt.setString(3, position.getPosition());
			pstmt.setString(4, piece.getColor().name());
			pstmt.executeUpdate();
		}

		JDBCConnector.closeConnection(con);
	}

	public Map<Position, Piece> findAllPiecesById(int roomId) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		Map<Position, Piece> pieces = new HashMap<>();

		String query = "SELECT piece_name, piece_position, piece_color FROM board WHERE room_id = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("piece_name");
			String position = rs.getString("piece_position");
			pieces.put(Position.of(position), PieceMapper.getInstance().findDBPiece(name));
		}

		JDBCConnector.closeConnection(con);

		return pieces;
	}

	public Piece findPieceByPosition(String position) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "SELECT piece_name FROM board WHERE piece_position = ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, position);
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			return Blank.getInstance();
		}

		Piece piece = PieceMapper.getInstance().findDBPiece(rs.getString("piece_name"));

		JDBCConnector.closeConnection(con);

		return piece;
	}

	public void updatePieceByPosition(String currentPosition, String nextPosition) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "UPDATE board SET piece_position = ? WHERE piece_position = ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, nextPosition);
		pstmt.setString(2, currentPosition);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public void deletePieceByPosition(String position) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "DELETE FROM board WHERE piece_position = ?";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, position);
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}

	public void addPieceByPosition(int roomId, Position position, Piece piece) throws SQLException {
		Connection con = JDBCConnector.getConnection();

		String query = "INSERT INTO board(room_id, piece_name, piece_position, piece_color) VALUES (?, ?, ?, ?)";

		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, roomId);
		pstmt.setString(2, piece.getSymbol());
		pstmt.setString(3, position.getPosition());
		pstmt.setString(4, piece.getColor().name());
		pstmt.executeUpdate();

		JDBCConnector.closeConnection(con);
	}
}
