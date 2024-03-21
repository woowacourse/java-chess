package chess.domain.strategy;

import chess.domain.Position;
import chess.domain.PositionDifference;

public class BlackPawnFirstMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position currentPosition, Position newPosition) {
        PositionDifference positionDifference = currentPosition.calculateDifference(newPosition);

        boolean canMoveStraight = positionDifference.isWithinVerticalRange(1, 2);
        boolean canMoveDiagonal =
                positionDifference.isWithinVerticalRange(1, 1) && positionDifference.isMagnitudeEqual();

        return canMoveStraight || canMoveDiagonal;
    }
}
