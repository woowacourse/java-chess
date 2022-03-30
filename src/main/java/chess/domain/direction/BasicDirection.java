package chess.domain.direction;

import java.util.function.BiPredicate;

import chess.domain.position.Position;

public enum BasicDirection implements Direction {

    SOUTH((rowDifference, columnDifference) ->
            rowDifference > 0 && columnDifference == 0),
    NORTH((rowDifference, columnDifference) ->
            rowDifference < 0 && columnDifference == 0),
    WEST((rowDifference, columnDifference) ->
            rowDifference == 0 && columnDifference > 0),
    EAST((rowDifference, columnDifference) ->
            rowDifference == 0 && columnDifference < 0);

    private final BiPredicate<Integer, Integer> directionPredicate;

    BasicDirection(BiPredicate<Integer, Integer> directionPredicate) {
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
