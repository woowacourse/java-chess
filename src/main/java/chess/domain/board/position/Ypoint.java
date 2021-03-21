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
        return Arrays.stream(values())
            .filter(ypoint -> ypoint.value == this.value + 1)
            .findFirst()
            .orElse(this);
    }

    public Ypoint up(int value) {
        return Arrays.stream(values())
            .filter(ypoint -> ypoint.value == this.value + value)
            .findFirst()
            .orElse(this);
    }

    public Ypoint down() {
        return Arrays.stream(values())
            .filter(ypoint -> ypoint.value == this.value - 1)
            .findFirst()
            .orElse(this);
    }

    public Ypoint down(int value) {
        System.out.println(Arrays.stream(values())
                .filter(ypoint -> ypoint.value == this.value - value)
                .findFirst()
                .orElse(this));
        return Arrays.stream(values())
            .filter(ypoint -> ypoint.value == this.value - value)
            .findFirst()
            .orElse(this);
    }
}
