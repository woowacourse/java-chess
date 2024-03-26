package domain.position;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Movement {

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

    Movement(BiPredicate<Position, Position> condition) {
        this.condition = condition;
    }

    public static Set<Movement> allMovements() {
        Set<Movement> values = Set.of(values());
        return values.stream()
                .filter(movement -> movement != NONE)
                .collect(Collectors.toSet());
    }

    public static Movement asMovement(Position source, Position target) {
        return Arrays.stream(values())
                .filter(movement -> movement.meetCondition(source, target))
                .findFirst()
                .orElse(NONE);
    }

    private boolean meetCondition(Position source, Position target) {
        return condition.test(source, target);
    }
}
