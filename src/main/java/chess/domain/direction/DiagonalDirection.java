package chess.domain.direction;

import java.util.function.BiPredicate;

import chess.domain.position.Position;

public enum DiagonalDirection implements Direction {

    NORTH_EAST((rowDifference, columnDifference) -> rowDifference < 0 && columnDifference < 0
            && rowDifference - columnDifference == 0),
    NORTH_WEST((rowDifference, columnDifference) -> rowDifference < 0 && columnDifference > 0
            && rowDifference + columnDifference == 0),
    SOUTH_EAST((rowDifference, columnDifference) -> rowDifference > 0 && columnDifference < 0
            && rowDifference + columnDifference == 0),
    SOUTH_WEST((rowDifference, columnDifference) -> rowDifference > 0 && columnDifference > 0
            && rowDifference - columnDifference == 0);

    private final BiPredicate<Integer, Integer> directionPredicate;

    DiagonalDirection(
            BiPredicate<Integer, Integer> quadrantPredicate) {
        this.directionPredicate = quadrantPredicate;
    }

    @Override
    public boolean isValidDirection(Position from, Position to) {
        return this.directionPredicate.test(from.subtractRow(to), from.subtractColumn(to));
    }

    @Override
    public boolean isDiagonal() {
        return true;
    }
}
