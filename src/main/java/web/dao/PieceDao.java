package web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.piece.Piece;
import web.dto.PieceDto;
import web.util.PieceFactory;

public class PieceDao {
	private static final PieceDao PIECE_DAO = new PieceDao();
	private static final String TABLE_NAME = "piece";

	private DBConnector connector = new DBConnector();

	private PieceDao() {
	}

	public static PieceDao getInstance() {
		return PIECE_DAO;
	}

	public void add(PieceDto pieceDto) throws SQLException {
		String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?)";
		try (
			PreparedStatement pstmt = connector.getConnection().prepareStatement(query)
		) {
			pstmt.setString(1, pieceDto.getSymbol());
			pstmt.setString(2, pieceDto.getPosition());
			pstmt.setString(3, pieceDto.getTeam());
			pstmt.executeUpdate();
		}
	}

	public List<Piece> findAll() throws SQLException {
		String query = "SELECT * FROM " + TABLE_NAME;
		List<Piece> pieces = new ArrayList<>();
		ResultSet rs;
		try (
			PreparedStatement pstmt = connector.getConnection().prepareStatement(query)
		) {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PieceFactory piece = PieceFactory.of(rs.getString("symbol"));
				pieces.add(piece.getGenerate().apply(rs.getString("position")));
			}
		}
		return pieces;
	}

	public void update(String originalPosition, String newPosition) throws SQLException {
		String query = "UPDATE " + TABLE_NAME + " SET position = ? WHERE position = ?";
		try (
			PreparedStatement pstmt = connector.getConnection().prepareStatement(query)
		) {
			pstmt.setString(1, newPosition);
			pstmt.setString(2, originalPosition);
			pstmt.executeUpdate();
		}
	}
}
