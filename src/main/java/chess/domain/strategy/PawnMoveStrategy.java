package chess.domain.strategy;

import chess.domain.MoveRange;
import chess.domain.Position;
import chess.domain.PositionDifference;

public abstract class PawnMoveStrategy implements MoveStrategy {
    protected final MoveRange straightMoveRange;
    protected final MoveRange diagonalMoveRange;

    public PawnMoveStrategy(MoveRange straightMoveRange, MoveRange diagonalMoveRange) {
        this.straightMoveRange = straightMoveRange;
        this.diagonalMoveRange = diagonalMoveRange;
    }

    @Override
    public boolean canMove(Position currentPosition, Position newPosition) {
        PositionDifference positionDifference = currentPosition.calculateDifference(newPosition);

        boolean canMoveStraight = positionDifference.isWithinVerticalRange(straightMoveRange);
        boolean canMoveDiagonal =
                positionDifference.isWithinVerticalRange(diagonalMoveRange) && positionDifference.isMagnitudeEqual();

        return canMoveStraight || canMoveDiagonal;
    }
}
