package chess.domain.position;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Direction {

    VERTICAL(Position::isVertical),
    HORIZONTAL(Position::isHorizontal),
    DIAGONAL(Position::isDiagonal),
    UNRELATED((source, target) -> true);

    private final BiPredicate<Position, Position> judgeDirection;

    Direction(BiPredicate<Position, Position> judgeDirection) {
        this.judgeDirection = judgeDirection;
    }

    public static Direction calculate(Position source, Position target) {
        return Stream.of(values())
                .filter(direction -> direction.judgeDirection.test(source, target))
                .findFirst()
                .orElse(UNRELATED);
    }

    public boolean isUnrelated() {
        return this == UNRELATED;
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
