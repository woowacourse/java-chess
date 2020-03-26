package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {
	public King(String color, String symbol) {
		super(color, symbol);
	}

	@Override
	public void move(Position source, Position target) {
		if (!Direction.canGoOneTimeTarget(Direction.everyDirection(), source, target)) {
			throw new UnsupportedOperationException("target 위치로 갈 수 없습니다.");
		}
	}
}
