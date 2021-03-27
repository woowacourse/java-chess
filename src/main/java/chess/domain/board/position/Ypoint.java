package chess.domain.board.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Ypoint {
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ONE("1", 1);

    private final String name;
    private final int value;

    Ypoint(final String name, final int value) {
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
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
        return Arrays.stream(values())
            .filter(ypoint -> ypoint.value == this.value - value)
            .findFirst()
            .orElse(this);
    }
}
