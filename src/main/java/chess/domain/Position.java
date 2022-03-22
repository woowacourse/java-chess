package chess.domain;

public class Position {

    private final char row;

    public Position(char row) {
        validateRowInRange(row);
        this.row = row;
    }

    private void validateRowInRange(char row) {
        if (row < 'a' || row > 'h') {
            throw new IllegalArgumentException("가로 위치는 a~h 범위에 포함되어야 합니다.");
        }
    }
}
