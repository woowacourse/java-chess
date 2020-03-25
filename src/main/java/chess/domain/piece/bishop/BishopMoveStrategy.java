package chess.domain.piece.bishop;

import chess.domain.piece.MoveStrategy;
import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class BishopMoveStrategy implements MoveStrategy {
	@Override
	public boolean isNotMovableTo(Position start, Position destination) {
		return !MovableAreaFactory.diagonalsOf(start).contains(destination);
	}
}
