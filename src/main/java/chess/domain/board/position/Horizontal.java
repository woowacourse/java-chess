package chess.domain.board.position;

import java.util.Arrays;

public enum Horizontal {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int index;

    Horizontal(final int index) {
        this.index = index;
    }

    public static Horizontal parse(final String input) {
        validateParseInt(input);
        return of(Integer.parseInt(input));
    }

    public static Horizontal of(final int index) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스판을 벗어난 가로열 위치입니다."));
    }

    private static void validateParseInt(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("가로열은 숫자를 입력해야합니다.");
        }
    }

    public int getIndex() {
        return index;
    }

    public int getDistance(final Horizontal other) {
        return Math.abs(this.minus(other));
    }

    public Horizontal add(final int h) {
        return of(index + h);
    }

    private int minus(final Horizontal other) {
        return this.index - other.index;
    }
}
