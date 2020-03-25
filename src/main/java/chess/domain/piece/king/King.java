package chess.domain.piece.king;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class King extends Piece {
	public King(Position position) {
		super(position, new KingMoveStrategy());
	}

	@Override
	public void moveTo(Position destination) {
		if (moveStrategy.isNotMovableTo(position, destination)) {
			throw new IllegalArgumentException("킹의 이동 범위가 아닙니다.");
		}
		position = destination;
	}
}