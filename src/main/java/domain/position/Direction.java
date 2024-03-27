package domain.position;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Direction {

    UP(Position::isUp),
    DOWN(Position::isDown),
    RIGHT(Position::isRight),
    LEFT(Position::isLeft),
    RIGHT_UP(Position::isRightUp),
    RIGHT_DOWN(Position::isRightDown),
    LEFT_UP(Position::isLeftUp),
    LEFT_DOWN(Position::isLeftDown),
    NONE((source, target) -> false),
    ;

    private final BiPredicate<Position, Position> condition;

    Direction(BiPredicate<Position, Position> condition) {
        this.condition = condition;
    }

    public static Direction asDirection(Position source, Position target) {
        return Arrays.stream(values())
                .filter(direction -> direction.meetCondition(source, target))
                .findFirst()
                .orElse(NONE);
    }

    public static Set<Direction> allDirections() {
        Set<Direction> values = Set.of(values());
        return values.stream()
                .filter(direction -> direction != NONE)
                .collect(Collectors.toSet());
    }

    public static Set<Direction> straightDirections() {
        return Set.of(UP, DOWN, RIGHT, LEFT);
    }

    public static Set<Direction> diagonalDirections() {
        return Set.of(RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);
    }

    private boolean meetCondition(Position source, Position target) {
        return condition.test(source, target);
    }
}
