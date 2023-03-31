package chess.domain.piece.coordinate;

import java.util.Arrays;

public enum Row {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private static final int INDEX_NUMBER_APPLIER = 1;
    private final String row;
    private final int rowIndex;

    Row(String row, int rowIndex) {
        this.row = row;
        this.rowIndex = rowIndex;
    }

    public static Row from(String targetRow) {
        return Arrays.stream(values())
            .filter(row -> row.row.equals(targetRow))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다"));
    }

    public int subtract(Row targetRow) {
        return this.rowIndex - targetRow.rowIndex;
    }

    public boolean isPawnStartRow() {
        return this == TWO || this == SEVEN;
    }

    public Row up(int num) {
        return values()[rowIndex + num - INDEX_NUMBER_APPLIER];
    }

    public int compareIndex(Row otherRow){
        return Integer.compare(otherRow.rowIndex,this.rowIndex);
    }

}
