package chess.domain.piece;

import chess.domain.board.Position;

public class Knight extends Piece {
	public Knight(String color, String name) {
		super(color, name);
	}

	@Override
	public void move(Position source, Position target) {
		if (!Direction.canGoOneTimeTarget(Direction.knightDirection(), source, target)) {
			throw new UnsupportedOperationException("target 위치로 갈 수 없습니다.");
		}
	}
}
