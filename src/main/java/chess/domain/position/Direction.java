package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    UP(true),
    DOWN(false);

    private final boolean destinationIsAbove;

    Direction(boolean destinationHigher) {
        this.destinationIsAbove = destinationHigher;
    }

    public static Direction from(boolean destinationIsAbove) {
        return Arrays.stream(values())
                .filter(direction -> direction.destinationIsAbove == destinationIsAbove)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("방향을 찾지 못했습니다"));
    }
}
