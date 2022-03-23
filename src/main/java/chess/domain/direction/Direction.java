package chess.domain.direction;

import chess.domain.Position;

public interface Direction {
	boolean confirm(Position from, Position to);
}
