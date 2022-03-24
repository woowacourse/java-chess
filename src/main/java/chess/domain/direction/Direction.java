package chess.domain.direction;

import chess.domain.Position;
import chess.domain.UnitPosition;

public interface Direction {

	boolean confirm(Position from, Position to);

	UnitPosition getUnitPosition();
}
