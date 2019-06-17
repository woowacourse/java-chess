package chess.model.unit;

public enum Column {
    A("A", 0),
    B("B", 1),
    C("C", 2),
    D("D", 3),
    E("E", 4),
    F("F", 5),
    G("G", 6),
    H("H", 7);

    private String name;
    private int columnIndex;

    Column(final String name, final int columnIndex) {
        this.name = name;
        this.columnIndex = columnIndex;
    }

    public int getIndexByName(final String name) {
        for (Column value : Column.values()) {
            if (value.name.toUpperCase().equals(name)) {
                return value.columnIndex;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 Column 값입니다.");
    }
}
