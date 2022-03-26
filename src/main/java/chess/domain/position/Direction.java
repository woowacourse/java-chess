package chess.domain.position;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Direction {

    VERTICAL(Position::isVertical),
    HORIZONTAL(Position::isHorizontal),
    DIAGONAL(Position::isDiagonal),
    IGNORE((source, target) -> true);

    private final BiPredicate<Position, Position> directionCalculator;

    Direction(BiPredicate<Position, Position> directionCalculator) {
        this.directionCalculator = directionCalculator;
    }

    public static Direction calculate(final Position source, final Position target) {
        return Stream.of(values())
                .filter(direction -> direction.directionCalculator.test(source, target))
                .findFirst()
                .orElseGet(() -> IGNORE);
    }

    public boolean isIgnore() {
        return this == IGNORE;
    }

    public boolean isVertical() {
        return this == VERTICAL;
    }

    public boolean isHorizontal() {
        return this == HORIZONTAL;
    }

    public boolean isDiagonal() {
        return this == DIAGONAL;
    }
}
