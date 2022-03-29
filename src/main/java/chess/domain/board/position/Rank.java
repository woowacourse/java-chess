package chess.domain.board.position;

import java.util.Arrays;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final String NO_NUMBER_ERROR_MESSAGE = "잘못된 위치 값 입니다.";

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public static Rank numberOf(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_NUMBER_ERROR_MESSAGE));
    }

    public int getNumber() {
        return number;
    }
}
