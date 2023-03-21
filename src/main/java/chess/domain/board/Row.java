package chess.domain.board;

import java.util.Arrays;

public enum Row {

    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8');

    private static final char CHAR_TO_INT = '0';
    private static final char INT_TO_CHAR = '0';

    private final char row;

    Row(final char row) {
        this.row = row;
    }

    public static Row fromByInput(final char input) {
        return Arrays.stream(Row.values())
            .filter(row -> row.row == input)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 Row입니다."));
    }

    public Row nextRow(final int next) {
        int RowNumer = this.row - CHAR_TO_INT;
        return fromByInput((char) (RowNumer + next + INT_TO_CHAR));
    }

    public int subPositionFromArrivePosition(final Row rowOfSource) {
        return this.row - rowOfSource.row;
    }

    public char getIndexOfRow() {
        return this.row;
    }
}
