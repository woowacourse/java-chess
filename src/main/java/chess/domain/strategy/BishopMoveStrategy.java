package chess.domain.strategy;

import chess.domain.Position;
import chess.domain.PositionDifference;

public class BishopMoveStrategy implements MoveStrategy {
    
    @Override
    public boolean canMove(final Position currentPosition, final Position newPosition) {
        PositionDifference positionDifference = currentPosition.calculateDifference(newPosition);

        return positionDifference.isMagnitudeEqual();
    }
}
