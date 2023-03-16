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

    public static int subPositionFromArrivePosition(final char start, final char end) {
        Row startRow = fromByInput(start);
        Row endRow = fromByInput(end);
        return endRow.row - startRow.row;
    }

    public char getIndexOfRow() {
        return this.row;
    }
}
