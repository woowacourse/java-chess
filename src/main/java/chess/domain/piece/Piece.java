package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
	protected Position position;
	protected final MoveStrategy moveStrategy;

	protected Piece(Position position, MoveStrategy moveStrategy) {
		this.position = position;
		this.moveStrategy = moveStrategy;
	}

	public abstract void moveTo(Position destination);

	public Position getPosition() {
		return position;
	}
}
