package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
	protected Position position;
	protected final MoveStrategy moveStrategy;

	public Piece(Position position, MoveStrategy moveStrategy) {
		this.position = position;
		this.moveStrategy = moveStrategy;
	}

	public void moveTo(Position destination) {
		if (moveStrategy.isNotMovableTo(position, destination)) {
			throw new IllegalArgumentException("기물의 이동 범위에 속하지 않습니다.");
		}
		position = destination;
	}

	public Position getPosition() {
		return position;
	}
}
