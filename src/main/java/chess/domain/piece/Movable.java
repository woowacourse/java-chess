package chess.domain.piece;

import chess.domain.position.Position;

public interface Movable {
	void move(Position targetPosition);

	boolean canMove(Position targetPosition);
}
