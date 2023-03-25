package chess.domain.piece.coordinate;

import java.util.Arrays;

public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String row;

    Row(String row) {
        this.row = row;
    }

    public static Row from(String targetRow) {
        return Arrays.stream(values())
            .filter(row -> row.row.equals(targetRow))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다"));
    }

    public int subtract(Row targetRow) {
        return this.ordinal() - targetRow.ordinal();
    }

    public boolean isPawnStartRow() {
        return this == TWO || this == SEVEN;
    }

    public Row up(int num) {
        return values()[ordinal() + num];
    }

}
