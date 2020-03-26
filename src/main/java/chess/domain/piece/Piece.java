package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public abstract class Piece implements Movable {
	protected String name;
	protected Side side;
	protected Position position;

	public Piece(Side side, Position position) {
		this.side = side;
		this.position = position;
	}

	@Override
	public void move(Position targetPosition) {
		this.position = targetPosition;
	}

	@Override
	public boolean canMove(Position targetPosition) {
		if (position.equals(targetPosition)) {
			return false;
		}
		return isInPath(targetPosition);
	}

	public abstract boolean isInPath(Position targetPosition);

	public boolean isSamePosition(Position position) {
		return this.position.equals(position);
	}
}
