package chess.domain.position;

public enum PositionX {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String name;
    private final int coordination;

    PositionX(final String name, final int coordination) {
        this.name = name;
        this.coordination = coordination;
    }

    public String getName() {
        return name;
    }

    public int getCoordination() {
        return coordination;
    }
}
