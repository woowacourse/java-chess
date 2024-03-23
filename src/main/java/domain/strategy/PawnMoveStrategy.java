package domain.strategy;

import domain.position.Position;
import domain.position.UnitVector;
import java.util.Set;

public abstract sealed class PawnMoveStrategy implements MoveStrategy permits BlackPawnMoveStrategy, WhitePawnMoveStrategy {
    private static final int ONE_STEP_SIZE = 1;

    @Override
    public boolean isMovable(Position source, Position destination, Set<Position> otherPiecesPosition) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();
        boolean isPieceExistOnDestination = otherPiecesPosition.contains(destination);
        UnitVector unitVector = UnitVector.of(rowDiff, colDiff);

        if (getCrossValidVectors().contains(unitVector)) {
            return isCrossMovable(rowDiff, colDiff) && isPieceExistOnDestination;
        }
        if (getStraightValidVector().equals(unitVector)) {
            return isStraightMovable(rowDiff, source, otherPiecesPosition) && !isPieceExistOnDestination;
        }
        return false;
    }

    protected abstract Set<UnitVector> getCrossValidVectors();

    protected abstract UnitVector getStraightValidVector();

    private boolean isCrossMovable(int rowDiff, int colDiff) {
        return isEqualSize(rowDiff, ONE_STEP_SIZE) && isEqualSize(colDiff, ONE_STEP_SIZE);
    }

    private boolean isStraightMovable(int rowDiff, Position source, Set<Position> otherPiecesPosition) {
        boolean isOneStepForwardMovable = isEqualSize(rowDiff, ONE_STEP_SIZE);
        boolean isTwoStepForwardMovable = checkTwoStepMovable(rowDiff, source, otherPiecesPosition);

        return isOneStepForwardMovable || isTwoStepForwardMovable;
    }

    private boolean checkTwoStepMovable(int rowDiff, Position source, Set<Position> otherPiecesPosition) {
        boolean isInitialMove = isInitialPosition(source);
        Position positionAfterOneStep = source.add(getStraightValidVector());
        boolean isBlockedByOtherPiece = otherPiecesPosition.contains(positionAfterOneStep);

        return isEqualSize(rowDiff, ONE_STEP_SIZE * 2) && isInitialMove && !isBlockedByOtherPiece;
    }

    protected abstract boolean isInitialPosition(final Position position);

    private boolean isEqualSize(int biPolarValue, int expectedSize) {
        return Math.abs(biPolarValue) == Math.abs(expectedSize);
    }
}
