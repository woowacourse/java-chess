package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class PieceAssembler {
	private PieceAssembler() {
	}

	public static PieceDto createDto(Piece piece) {
		Position position = piece.getPosition();
		Column col = position.getCol();
		Row row = position.getRow();
		return new PieceDto(PieceNameConverter.toName(piece), col.getValue(), row.getSymbol());
	}
}
