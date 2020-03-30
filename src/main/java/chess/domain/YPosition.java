package chess.domain;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public enum YPosition {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int number;

    YPosition(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static YPosition of(String rankValue) {
        return of(parseInt(rankValue));
    }

    public static YPosition of(int number) {
        return Arrays.stream(YPosition.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
