package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessPiece;
import chess.domain.Player;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;

public class PieceDAO {
	private static final String INSERT_PIECE =
			"insert into piece(player, piece_type, x_position, y_position, room_number) values(?,?,?,?,?)";
	private static final String DELETE_ALL_PIECES_QUERY =
			"delete from piece where room_number = ?";
	private static final String SELECT_PIECES_QUERY =
			"select player, piece_type, x_position, y_position from piece where room_number = ?";

	private static PieceDAO pieceDAO;
	private static Connection connection;

	private PieceDAO() {
	}

	public static PieceDAO getInstance(Connection connection) {
		if (pieceDAO == null) {
			pieceDAO = new PieceDAO();
		}
		PieceDAO.connection = connection;
		return pieceDAO;
	}

	public void addAllPieces(int roomNumber, List<Piece> pieces) throws SQLException {
		for (Piece piece : pieces) {
			addPiece(roomNumber, piece);
		}
	}

	private void addPiece(int roomNumber, Piece piece) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(INSERT_PIECE)) {
			pstmt.setString(1, piece.getPlayer().name());
			pstmt.setString(2, piece.getChessPiece().getType().name());
			pstmt.setInt(3, piece.getCoordinateX());
			pstmt.setInt(4, piece.getCoordinateY());
			pstmt.setInt(5, roomNumber);
			pstmt.executeUpdate();
		}
	}

	public void deleteAllPieces(int roomNumber) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(DELETE_ALL_PIECES_QUERY)) {
			pstmt.setInt(1, roomNumber);
			pstmt.executeUpdate();
		}
	}

	public List<Piece> getChessPieces(int roomNumber) throws SQLException {
		try (PreparedStatement pstmt = connection.prepareStatement(SELECT_PIECES_QUERY)) {
			pstmt.setInt(1, roomNumber);
			return getChessPiece(pstmt);
		}
	}

	private List<Piece> getChessPiece(PreparedStatement pstmt) throws SQLException {
		try (ResultSet rs = pstmt.executeQuery()) {
			List<Piece> pieces = new ArrayList<>();

			while (rs.next()) {
				Player player = Player.valueOf(rs.getString(1));
				Type type = Type.valueOf(rs.getString(2));
				Position position = Position.getPosition(rs.getInt(3), rs.getInt(4));
				pieces.add(ChessPiece.generatePiece(player, type, position));
			}
			return pieces;
		}
	}
}
