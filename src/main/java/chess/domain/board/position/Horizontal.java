package chess.domain.board.position;

import java.util.Arrays;

public enum Horizontal {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int index;

    Horizontal(final int index) {
        this.index = index;
    }

    static Horizontal indexOf(final String number) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == Integer.parseInt(number))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    static Horizontal of(final int index) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == index)
                .findAny()
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
