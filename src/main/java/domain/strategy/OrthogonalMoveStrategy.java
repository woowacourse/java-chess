package domain.strategy;

import domain.position.Position;
import domain.position.UnitVector;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static domain.position.UnitVector.*;

public class OrthogonalMoveStrategy implements ContinuousMoveStrategy {
    private static final Set<UnitVector> acceptableVectors = Set.of(UP, RIGHT, DOWN, LEFT);

    @Override
    public boolean isMovable(final Position source, final Position destination, final Set<Position> otherPiecesPosition) {
        UnitVector optimalVector = findOptimalVector(source, destination);

        if (!acceptableVectors.contains(optimalVector)) {
            return false;
        }

        List<Position> movePaths = Stream.iterate(source, position -> position.add(optimalVector))
                .takeWhile(position -> !position.equals(destination) && !otherPiecesPosition.contains(position))
                .limit(8)
                .toList();

        return isReachable(destination, optimalVector, movePaths);
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
