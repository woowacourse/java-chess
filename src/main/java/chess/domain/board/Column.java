package chess.domain.board;

import java.util.Arrays;

public enum Column {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char column;

    Column(final char column) {
        this.column = column;
    }

    public static Column fromByInput(final char input) {
        return Arrays.stream(Column.values())
                .filter(col -> col.column == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 column입니다."));
    }

    public static int subPositionFromArrivePosition(final char start, final char end) {
        Column startColumn = fromByInput(start);
        Column endColumn = fromByInput(end);
        return endColumn.column - startColumn.column;
    }

    public char getIndexOfCol() {
        return this.column;
    }
}
