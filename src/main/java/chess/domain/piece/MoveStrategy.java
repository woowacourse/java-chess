package chess.domain.piece;

import chess.domain.position.Position;

public interface MoveStrategy {
	boolean isNotMovableTo(Position start, Position destination);
}
