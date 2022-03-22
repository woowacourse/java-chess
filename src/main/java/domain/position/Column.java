package domain.position;

public enum Column {

    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String row;

    Column(final String row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Column{" +
                "row='" + row + '\'' +
                '}';
    }
}
