package chess.domain.board.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

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

    public int getValue() {
        return value;
    }

    public Ypoint up() {
        return find(addCondition(1));
    }

    public Ypoint up(int value) {
        return find(addCondition(value));
    }

    public Ypoint down() {
        return find(subtractCondition(1));
    }

    public Ypoint down(int value) {
        return find(subtractCondition(value));
    }

    public Ypoint find(final Predicate<Ypoint> condition) {
        return Arrays.stream(values())
                .filter(condition)
                .findFirst()
                .orElse(this);
    }

    private Predicate<Ypoint> addCondition(int value) {
        return ypoint -> ypoint.value == this.value + value;
    }

    private Predicate<Ypoint> subtractCondition(int value) {
        return ypoint -> ypoint.value == this.value - value;
    }
}
