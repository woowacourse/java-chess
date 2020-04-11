package chess.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.piece.Piece;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
public class WhitePieceRowMapper implements RowMapper<Piece> {
	@Override
	public Piece mapRow(ResultSet rs) throws SQLException {
		return mapPiece(rs);
	}

	private Piece mapPiece(ResultSet rs) throws SQLException {
		String pieceSymbol = rs.getString("pieceSymbol");
		String pieceState = rs.getString("pieceState");

		return WhitePieceMapper.mappingBy(pieceSymbol, pieceState);
	}
}
