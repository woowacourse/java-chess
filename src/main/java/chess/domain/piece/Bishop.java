package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {
	public Bishop(String color, String symbol) {
		super(color, symbol);
	}

	@Override
	public void move(Position source, Position target) {
		if (!Direction.canGoManyTimesTarget(Direction.diagonalDirection(), source, target)) {
			throw new UnsupportedOperationException("target 위치로 갈 수 없습니다.");
		}
	}
}
