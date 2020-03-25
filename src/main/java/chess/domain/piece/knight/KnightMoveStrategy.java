package chess.domain.piece.knight;

import chess.domain.piece.MoveStrategy;
import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class KnightMoveStrategy implements MoveStrategy {
	@Override
	public boolean isNotMovableTo(Position start, Position destination) {
		return !MovableAreaFactory.knightOf(start).contains(destination);
	}
}
