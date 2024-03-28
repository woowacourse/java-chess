package chess.domain.strategy;

import chess.domain.pieceInfo.Position;
import chess.domain.pieceInfo.PositionDifference;

public class WhitePawnNotFirstMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(final Position currentPosition, final Position newPosition) {
        PositionDifference positionDifference = currentPosition.calculateDifference(newPosition);

        boolean canMoveStraight = positionDifference.isWithinVerticalRange(-1, -1);
        boolean canMoveDiagonal =
                positionDifference.isWithinVerticalRange(-1, -1) && positionDifference.isMagnitudeEqual();

        return canMoveStraight || canMoveDiagonal;
    }
}
