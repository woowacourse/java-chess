package chess.domain.board;

import java.util.Arrays;

public enum Col {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char column;

    Col(final char column) {
        this.column = column;
    }

    public static Col from(final char column) {
        return Arrays.stream(Col.values())
                .filter(col -> col.column == column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 column입니다."));
    }

    public int calculateSubstitutionFromArrivalPosition(final int arrivePosition) {
        int newColPosition = arrivePosition - this.column;
        return newColPosition;
    }
}
