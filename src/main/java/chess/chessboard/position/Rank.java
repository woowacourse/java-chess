package chess.chessboard.position;

import java.util.Arrays;

public enum Rank {

    EIGHT(8, '8'),
    SEVEN(7, '7'),
    SIX(6, '6'),
    FIVE(5, '5'),
    FOUR(4, '4'),
    THREE(3, '3'),
    TWO(2, '2'),
    ONE(1, '1'),
    ;

    private final int value;
    private final char name;

    Rank(final int value, final char name) {
        this.value = value;
        this.name = name;
    }

    public Rank add(final int row) {
        return Rank.of(value + row);
    }

    public boolean canAdd(final int row) {
        return value + row >= ONE.value && value + row <= EIGHT.value;
    }

    public static Rank of(final int otherValue) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == otherValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public static Rank of(final char name) {
        return Arrays.stream(values())
                .filter(rank -> rank.name == name)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public Character getName() {
        return this.name;
    }
}
