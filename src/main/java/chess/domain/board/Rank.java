package chess.domain.board;

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

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 8;

    private final int number;
    private final String letter;

    Rank(int number, String letter) {
        this.number = number;
        this.letter = letter;
    }

    public static Rank letterOf(String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.letter.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치 값 입니다."));
    }

    public Rank plus(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == (number + value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치 값 입니다."));
    }

    public boolean isMoveInRange(int value) {
        return MINIMUM <= value && value <= MAXIMUM;
    }

    public int getNumber() {
        return number;
    }
}
