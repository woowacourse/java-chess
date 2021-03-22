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

    static Horizontal parse(final String number) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == Integer.parseInt(number))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    static Horizontal of(final int index) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == index)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex() {
        return index;
    }

    public int getDistance(final Horizontal other) {
        return Math.abs(this.index - other.getIndex());
    }

    public Horizontal add(final int h) {
        return of(index + h);
    }
}
