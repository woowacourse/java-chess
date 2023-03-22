package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private final int value;
    private final String command;

    Rank(final int value, final String command) {
        this.value = value;
        this.command = command;
    }

    static Rank from(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("랭크는 1~8의 숫자로 입력해 주세요"));
    }

    static Rank from(String command) {
        return Arrays.stream(values())
                .filter(rank -> rank.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("랭크는 1~8의 숫자로 입력해 주세요"));
    }

    int gapWith(Rank rank) {
        return this.value - rank.value;
    }

    public boolean isSame(final int rank) {
        return this.value == rank;
    }

    public int value() {
        return value;
    }

    public String command() {
        return command;
    }
}
