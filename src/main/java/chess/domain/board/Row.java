package chess.domain.board;

import java.util.Arrays;

public enum Row {

    ONE(7, '1'),
    TWO(6, '2'),
    THREE(5, '3'),
    FOUR(4, '4'),
    FIVE(3, '5'),
    SIX(2, '6'),
    SEVEN(1, '7'),
    EIGHT(0, '8');

    private final int index;
    private final char row;

    Row(int index, char row) {
        this.index = index;
        this.row = row;
    }

    public static Row findRow(char input) {
        return Arrays.stream(values())
            .filter(value -> value.row == input)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static Row findRowByIndex(int input) {
        return Arrays.stream(values())
            .filter(value -> value.index == input)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public int getIndex() {
        return index;
    }

    public Character getString() {
        return row;
    }
}
