package chess.domain.board.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Ypoint {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Ypoint(final int value) {
        this.value = value;
    }

    public static List<Ypoint> getBlackPoint() {
        return Collections.singletonList(EIGHT);
    }

    public static List<Ypoint> getWhitePoint() {
        return Collections.singletonList(ONE);
    }

    public static List<Ypoint> getEmptyPoints() {
        return Arrays.asList(SIX, FIVE, FOUR, THREE);
    }

    public static List<Ypoint> getBlackPawnPoint() {
        return Collections.singletonList(SEVEN);
    }

    public static List<Ypoint> getWhitePawnPoint() {
        return Collections.singletonList(TWO);
    }

    public int getValue() {
        return value;
    }

    private Ypoint of(int value) {
        return Arrays.stream(values())
                .filter(ypoint -> ypoint.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Ypoint up() {
        if (this == EIGHT) {
            return this;
        }

        return of(value + 1);
    }

    public Ypoint down() {
        if (this == ONE) {
            return this;
        }

        return of(value - 1);
    }
}
