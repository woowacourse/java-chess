package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {
	public Queen(String color, String symbol) {
		super(color, symbol);
	}

	@Override
	public void move(Position source, Position target) {
		if (!Direction.canGoManyTimesTarget(Direction.everyDirection(), source, target)) {
			throw new UnsupportedOperationException("target 위치로 갈 수 없습니다.");
		}
	}
}
