package chess.domain.board.position;

import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.Arrays;
import java.util.Collections;

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

    public static Horizontal parse(final String number) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == Integer.parseInt(number))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Horizontal of(final int index) {
        return Arrays.stream(Horizontal.values())
                .filter(h -> h.index == index)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Horizontal[] reversedValues() {
        Horizontal[] values = values();
        Arrays.sort(values, Collections.reverseOrder());
        return values;
    }

    public Horizontal add(final Direction direction, final Distance distance) {
        return of(index + direction.getY() * distance.getValue());
    }

    public int getIndex() {
        return index;
    }
}
