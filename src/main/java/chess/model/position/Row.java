package chess.model.position;

public enum Row {
    EIGHT("8", 0),
    SEVEN("7", 1),
    SIX("6", 2),
    FIVE("5", 3),
    FOUR("4", 4),
    THREE("3", 5),
    TWO("2", 6),
    ONE("1", 7);

    private static final char START_ROW = '8';

    private final String displayName;
    private final int index;

    Row(String displayName, int index) {
        this.displayName = displayName;
        this.index = index;
    }

    public static Row findRow(char row) {
        int rowIndex = START_ROW - row;
        return findRow(rowIndex);
    }

    public static Row findRow(int index) {
        if (index < 0 || index >= values().length) {
            throw new IllegalArgumentException("좌표의 row는 1이상 8이하의 값만 가능합니다.");
        }
        return values()[index];
    }

    public Row add(int offset) {
        int nextIndex = this.index + offset;
        return findRow(nextIndex);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
