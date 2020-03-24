package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {
	boolean canMove(Position currentPosition, Position nextPosition);
}
