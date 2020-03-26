package chess.domain.piece;

import java.util.Map;

import chess.domain.board.Position;

public class WhitePieces {
	private final Map<Position, Piece> pieces;

	public WhitePieces(final Map<Position, Piece> pieces) {
		this.pieces = pieces;
	}

	public void moveFromTo(Position source, Position target) {
		// 내것이 맞는지val();
		if (!pieces.containsKey(source)) {
			throw new UnsupportedOperationException("움직일 수 있는 말이 아닙니다.");
		}
	}
}
