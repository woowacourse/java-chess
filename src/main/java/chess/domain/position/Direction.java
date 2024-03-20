package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    UP(true),
    DOWN(false);

    private final boolean destinationHigher;

    Direction(boolean destinationHigher) {
        this.destinationHigher = destinationHigher;
    }

    public static Direction from(boolean destinationHigher) {
        return Arrays.stream(values())
                .filter(direction -> direction.destinationHigher == destinationHigher)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("방향을 찾지 못했습니다"));
    }
}
