package domain.strategy;

import domain.position.Position;
import domain.position.UnitVector;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ContinuousMoveStrategy implements MoveStrategy {
    private final Set<UnitVector> acceptableVectors;
    private final int moveBound;

    public ContinuousMoveStrategy(Set<UnitVector> acceptableVectors, int moveBound) {
        this.acceptableVectors = acceptableVectors;
        this.moveBound = moveBound;
    }

    @Override
    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        UnitVector optimalVector = findOptimalVector(source, destination);

        if (!acceptableVectors.contains(optimalVector)) {
            return false;
        }

        List<Position> movePaths = Stream.iterate(source,
                        position -> isContinuable(position, destination, piecePositions),
                        position -> position.add(optimalVector))
                .limit(moveBound)
                .toList();

        return isReachable(destination, optimalVector, movePaths);
    }

    private boolean isContinuable(final Position current, final Position destination, final Set<Position> piecePositions) {
        boolean isReachedDestination = current.equals(destination);
        boolean isOtherPieceExist = piecePositions.contains(current);
        return !isReachedDestination && !isOtherPieceExist;
    }

    private boolean isReachable(final Position destination, final UnitVector optimalVector, final List<Position> movePaths) {
        Position finalPosition = movePaths.get(movePaths.size() - 1).add(optimalVector);
        return finalPosition.equals(destination);
    }

    private UnitVector findOptimalVector(final Position source, final Position destination) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();

        return UnitVector.of(rowDiff, colDiff);
    }
}
