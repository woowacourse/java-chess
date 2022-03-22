package chess.domain;

public class Position {

    private static final char MIN_ROW = 'a';
    private static final char MAX_ROW = 'h';

    private final char row;

    public Position(char row) {
        validateRowInRange(row);
        this.row = row;
    }

    private void validateRowInRange(char row) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException("가로 위치는 a~h 범위에 포함되어야 합니다.");
        }
    }
}
