package domain.piece;

import java.util.function.Predicate;

public enum Direction {
    UP(integer -> integer > 0),
    DOWN(integer -> integer < 0),
    STOP(integer -> integer == 0);

    private final Predicate<Integer> foward;

    Direction(Predicate<Integer> foward) {
        this.foward = foward;
    }

    public boolean isForward(int distance) {
        return foward.test(distance);
    }
}
