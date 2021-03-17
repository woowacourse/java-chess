package chess.board;

public enum Column {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String xCoordinate;
    private final int index;

    Column(String xCoordinate, int index) {
        this.xCoordinate = xCoordinate;
        this.index = index;
    }

    public String getXCoordinate() {
        return xCoordinate;
    }

    public int getXIndex() {
        return index;
    }
}
