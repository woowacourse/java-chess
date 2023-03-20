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

    private static final char CHAR_TO_INT = '0';
    private static final char INT_TO_CHAR = '0';

    private final char column;

    Col(final char column) {
        this.column = column;
    }

    public static Col fromByInput(final char input) {
        return Arrays.stream(Col.values())
                .filter(col -> col.column == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 column입니다."));
    }

    public Col nextCol(final int next) {
        int columNumber = this.column - CHAR_TO_INT;
        System.out.println((char) (columNumber + next + INT_TO_CHAR));
        return fromByInput((char) (columNumber + next + INT_TO_CHAR));
    }

    public int getCol() {
        return this.column;
    }

    public int subPositionFromArrivePosition(final Col colOfSource) {
        return this.column - colOfSource.column;
    }
}
