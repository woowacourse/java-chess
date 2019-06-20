package chess.domain.direction;

import chess.domain.Position;

public interface Direction {
    Position simulateUnitMove(Position position, boolean isReverseDirection);
}
