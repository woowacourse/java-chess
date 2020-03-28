package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public interface MovePattern {
	MovePattern valueOf(Position source, Position target);

	Direction getDirection();

	int getCount();

	boolean isMatchedPoints(Position source, Position target);
}
