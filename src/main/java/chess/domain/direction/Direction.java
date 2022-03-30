package chess.domain.direction;

import chess.domain.position.Position;
import chess.domain.position.UnitPosition;

public interface Direction {

	boolean confirm(Position from, Position to);

	boolean isDiagonal();

	UnitPosition getUnitPosition();
}
