package chess.domain.direction;

import chess.domain.position.Position;
import java.util.function.BiPredicate;

public enum KnightDirection implements Direction {
    NORTH_NORTH_EAST((rowDifference, columnDifference) -> rowDifference == -2 && columnDifference == -1),
    EAST_NORTH_EAST((rowDifference, columnDifference) -> rowDifference == -1 && columnDifference == -2),
    EAST_SOUTH_EAST((rowDifference, columnDifference) -> rowDifference == 1 && columnDifference == -2),
    SOUTH_SOUTH_EAST((rowDifference, columnDifference) -> rowDifference == 2 && columnDifference == -1),
    SOUTH_SOUTH_WEST((rowDifference, columnDifference) -> rowDifference == 2 && columnDifference == 1),
    WEST_SOUTH_WEST((rowDifference, columnDifference) -> rowDifference == 1 && columnDifference == 2),
    WEST_NORTH_WEST((rowDifference, columnDifference) -> rowDifference == -1 && columnDifference == 2),
    NORTH_NORTH_WEST((rowDifference, columnDifference) -> rowDifference == -2 && columnDifference == 1);

    private final BiPredicate<Integer, Integer> directionPredicate;

    KnightDirection(BiPredicate<Integer, Integer> directionPredicate) {
        this.directionPredicate = directionPredicate;
    }

    @Override
    public boolean isValidDirection(Position from, Position to) {
        return this.directionPredicate.test(from.subtractRow(to), from.subtractColumn(to));
    }

    @Override
    public boolean isDiagonal() {
        return false;
    }
}
