package domain.strategy;

import domain.position.Position;
import domain.position.CommonMovementDirection;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ContinuousMoveStrategy implements MoveStrategy {
    private final Set<CommonMovementDirection> acceptableVectors;
    private final int moveBound;

    public ContinuousMoveStrategy(Set<CommonMovementDirection> acceptableVectors, int moveBound) {
        this.acceptableVectors = acceptableVectors;
        this.moveBound = moveBound;
    }

    @Override
    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        CommonMovementDirection optimalVector = findOptimalVector(source, destination);

        if (!acceptableVectors.contains(optimalVector)) {
            return false;
        }

        List<Position> movePaths = Stream.iterate(source, position -> position.next(optimalVector))
                .takeWhile(position -> isContinuable(position, destination, piecePositions))
                .limit(moveBound)
                .toList();

        return isReachable(destination, optimalVector, movePaths);
    }

    private static boolean isContinuable(final Position current, final Position destination, final Set<Position> piecePositions) {
        boolean isReachedDestination = current.equals(destination);
        boolean isOtherPieceExist = piecePositions.contains(current);
        return !isReachedDestination && !isOtherPieceExist;
    }

    private boolean isReachable(final Position destination, final CommonMovementDirection optimalVector, final List<Position> movePaths) {
        Position finalPosition = movePaths.get(movePaths.size() - 1).next(optimalVector);
        return finalPosition.equals(destination);
    }

    private CommonMovementDirection findOptimalVector(final Position source, final Position destination) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();

        return CommonMovementDirection.calculateDirection(source, destination);
    }
}
