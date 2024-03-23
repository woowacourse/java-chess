package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    EIGHT(8, "8"),
    SEVEN(7, "7"),
    SIX(6, "6"),
    FIVE(5, "5"),
    FOUR(4, "4"),
    THREE(3, "3"),
    TWO(2, "2"),
    ONE(1, "1"),
    ;

    private final int value;
    private final String command;

    Rank(int value, String command) {
        this.value = value;
        this.command = command;
    }

    public static Rank from(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank입니다."));
    }

    public Rank add(int y) {
        int newY = this.value + y;
        return Rank.from(newY);
    }

    public boolean isInRange(int nextFile) {
        return ONE.value <= nextFile && nextFile <= EIGHT.value;
    }

    public int getValue() {
        return value;
    }

    public String getCommand() {
        return command;
    }
}
