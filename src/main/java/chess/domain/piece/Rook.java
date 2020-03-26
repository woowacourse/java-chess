package chess.domain.piece;

import chess.domain.board.Position;

public class Rook extends Piece {
	public Rook(String color, String name) {
		super(color, name);
	}

	@Override
	public void move(Position source, Position target) {
		if (!Direction.canGoManyTimesTarget(Direction.linearDirection(), source, target)) {
			throw new UnsupportedOperationException("target 위치로 갈 수 없습니다.");
		}
	}
}
