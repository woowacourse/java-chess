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

    private final int number;

    private final String condition;

    Rank(int number, String condition) {
        this.number = number;
        this.condition = condition;
    }

    public static Rank numberOf(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.number == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치 값 입니다."));
    }

    public static Rank conditionOf(String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.condition.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치 값 입니다."));
    }

    public String getCondition() {
        return condition;
    }

    public int getNumber() {
        return number;
    }
}
