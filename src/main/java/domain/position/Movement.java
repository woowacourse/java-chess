package domain.position;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Movement {

    UP((source, target) -> source.isSameFile(target) && source.isUp(target)),
    DOWN((source, target) -> source.isSameFile(target) && source.isDown(target)),
    RIGHT((source, target) -> source.isRight(target) && source.isSameRank(target)),
    LEFT((source, target) -> source.isLeft(target) && source.isSameRank(target)),
    RIGHT_UP((source, target) -> source.isRightUp(target) && source.isSameDistance(target)),
    RIGHT_DOWN((source, target) -> source.isRightDown(target) && source.isSameDistance(target)),
    LEFT_UP((source, target) -> source.isLeftUp(target) && source.isSameDistance(target)),
    LEFT_DOWN((source, target) -> source.isLeftDown(target) && source.isSameDistance(target)),
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
