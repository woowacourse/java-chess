package chess.domain.strategy;

import chess.domain.Position;
import chess.domain.PositionDifference;

public class WhitePawnFirstMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position currentPosition, Position newPosition) {
        PositionDifference positionDifference = currentPosition.calculateDifference(newPosition);

        boolean canMoveStraight = positionDifference.isWithinVerticalRange(-2, -1);
        boolean canMoveDiagonal =
                positionDifference.isWithinVerticalRange(-1, -1) && positionDifference.isMagnitudeEqual();

        return canMoveStraight || canMoveDiagonal;
    }
}
