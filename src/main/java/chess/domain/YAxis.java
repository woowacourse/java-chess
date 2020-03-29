package chess.domain;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public enum YAxis {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int number;

    YAxis(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static YAxis of(String rankValue) {
        return of(parseInt(rankValue));
    }

    public static YAxis of(int number) {
        return Arrays.stream(YAxis.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
