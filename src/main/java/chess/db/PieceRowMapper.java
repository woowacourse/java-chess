package chess.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import chess.domain.piece.Piece;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
public class PieceRowMapper implements RowMapper<Piece> {
	@Override
	public Piece mapRow(ResultSet rs) throws SQLException {
		return mapPiece(rs);
	}

	private Piece mapPiece(ResultSet rs) throws SQLException {
		String pieceSymbol = rs.getString("pieceSymbol");
		String pieceState = rs.getString("pieceState");

		Piece piece = WhitePieceMapper.mappingBy(pieceSymbol, pieceState);
		if (Objects.isNull(piece)) {
			piece = BlackPieceMapper.mappingBy(pieceSymbol, pieceState);
		}
		return piece;
	}
}
