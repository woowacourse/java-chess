package chess.model.position;

public enum Column {

    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private static final char START_COLUMN = 'a';

    private final int index;

    Column(int index) {
        this.index = index;
    }

    public static Column findColumn(char column) {
        int columnIndex = column - START_COLUMN;
        return findColumn(columnIndex);
    }

    public static Column findColumn(int index) {
        if (index < 0 || index >= values().length) {
            throw new IllegalArgumentException("좌표의 column은 1이상 8이하의 값만 가능합니다.");
        }
        return values()[index];
    }

    public Column add(int offset) {
        int nextIndex = this.index + offset;
        return findColumn(nextIndex);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return name();
    }
}
