package chess.domain.piece.position;

import java.util.Arrays;

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

    public static YPosition of(String rankValue) {
        return of(Integer.parseInt(rankValue));
    }

    public static YPosition of(int number) {
        return Arrays.stream(YPosition.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getNumber() {
        return number;
    }

    public int calculateDistance(YPosition yPosition) {
        return this.number - yPosition.number;
    }
}
