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

    public static Col fromByInput(final char column) {
        return Arrays.stream(Col.values())
                .filter(col -> col.column == column)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 column입니다."));
    }

    public static int subPositionFromArrivePosition(final char start, final char end) {
        Col startCol = fromByInput(start);
        Col endCol = fromByInput(end);

        int newRowPosition = endCol.column - startCol.column;
        return newRowPosition;
    }
}
