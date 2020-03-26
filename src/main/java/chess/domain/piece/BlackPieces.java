package chess.domain.piece;

import java.util.Map;

import chess.domain.board.Position;

public class BlackPieces {
	private final Map<Position, Piece> pieces;

	public BlackPieces(final Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public void moveFromTo(Position source, Position target) {
		if (!pieces.containsKey(source)) {
			throw new UnsupportedOperationException("움직일 수 있는 말이 아닙니다.");
		}
	}

	public boolean hasPiece(Position source) {
		return pieces.containsKey(source);
	}
}
