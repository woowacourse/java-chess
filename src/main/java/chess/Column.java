package chess;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String xCoordinate;

    Column(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getXCoordinate() {
        return xCoordinate;
    }
}
