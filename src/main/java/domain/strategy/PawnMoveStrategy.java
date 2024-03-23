package domain.strategy;

import domain.position.Position;
import domain.position.UnitVector;
import java.util.Set;

public abstract sealed class PawnMoveStrategy implements MoveStrategy permits BlackPawnMoveStrategy,
        WhitePawnMoveStrategy {
    @Override
    public boolean isMovable(Position source, Position destination, Set<Position> otherPiecesPosition) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();
        UnitVector unitVector = UnitVector.of(rowDiff, colDiff);
        boolean isPieceExistOnDestination = otherPiecesPosition.contains(destination);

        if (isPieceExistOnDestination) {
            return isCrossMovable(unitVector, colDiff);
        }
        return isStraightMovable(unitVector, rowDiff, isInitialPosition(source));
    }

    private boolean isCrossMovable(UnitVector unitVector, int colDiff) {
        Set<UnitVector> validVectors = getCrossValidVectors();
        return validVectors.contains(unitVector) && Math.abs(colDiff) == 1;
    }

    protected abstract Set<UnitVector> getCrossValidVectors();

    private boolean isStraightMovable(UnitVector unitVector, int rowDiff, boolean isInitialMove) {
        UnitVector validVector = getStraightValidVector();

        if (!unitVector.equals(validVector)) {
            return false;
        }

        boolean isOneStepForwardMovable = isEqualSize(rowDiff, 1);
        boolean isTwoStepForwardMovable = isEqualSize(rowDiff, 2) && isInitialMove;

        return isOneStepForwardMovable || isTwoStepForwardMovable;
    }

    protected abstract UnitVector getStraightValidVector();

    private boolean isEqualSize(int biPolarValue, int expectedSize) {
        return Math.abs(biPolarValue) == Math.abs(expectedSize);
    }

    protected abstract boolean isInitialPosition(final Position position);
}
