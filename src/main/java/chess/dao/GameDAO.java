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
		String query = "DELETE FROM board WHERE room_id = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);
		) {
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
		}
	}

	public void addAllPiecesById(int roomId, Pieces pieces) throws SQLException {
		String query = "INSERT INTO board(room_id, piece_name, piece_position, piece_color) VALUES (?, ?, ?, ?)";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);
		) {
			for (Position position : pieces.getPieces().keySet()) {
				Piece piece = pieces.getPieceByPosition(position);

				pstmt.setInt(1, roomId);
				pstmt.setString(2, piece.getSymbol());
				pstmt.setString(3, position.getPosition());
				pstmt.setString(4, piece.getColor().name());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
	}

	public Map<Position, Piece> findAllPiecesById(int roomId) throws SQLException {
		String query = "SELECT piece_name, piece_position, piece_color FROM board WHERE room_id = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);
		) {
			Map<Position, Piece> pieces = new HashMap<>();

			pstmt.setInt(1, roomId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					String name = rs.getString("piece_name");
					String position = rs.getString("piece_position");
					pieces.put(Position.of(position), PieceMapper.getInstance().findDBPiece(name));
				}
			}
			return pieces;
		}
	}

	public Piece findPieceByPosition(String position) throws SQLException {
		String query = "SELECT piece_name FROM board WHERE piece_position = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);
		) {
			pstmt.setString(1, position);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (!rs.next()) {
					return Blank.getInstance();
				}
				return PieceMapper.getInstance().findDBPiece(rs.getString("piece_name"));
			}
		}
	}

	public void updatePieceByPosition(String currentPosition, String nextPosition) throws SQLException {
		String query = "UPDATE board SET piece_position = ? WHERE piece_position = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)
		) {
			pstmt.setString(1, nextPosition);
			pstmt.setString(2, currentPosition);
			pstmt.executeUpdate();
		}
	}

	public void deletePieceByPosition(String position) throws SQLException {
		String query = "DELETE FROM board WHERE piece_position = ?";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);
		) {
			pstmt.setString(1, position);
			pstmt.executeUpdate();
		}
	}

	public void addPieceByPosition(int roomId, Position position, Piece piece) throws SQLException {
		String query = "INSERT INTO board(room_id, piece_name, piece_position, piece_color) VALUES (?, ?, ?, ?)";

		try (Connection con = JDBCConnector.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)
		) {
			pstmt.setInt(1, roomId);
			pstmt.setString(2, piece.getSymbol());
			pstmt.setString(3, position.getPosition());
			pstmt.setString(4, piece.getColor().name());
			pstmt.executeUpdate();
		}
	}
}
