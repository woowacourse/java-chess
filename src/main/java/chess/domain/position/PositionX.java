package chess.domain.position;

public enum PositionX {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String name;
    private final int coordination;

    PositionX(final String name, final int coordination) {
        this.name = name;
        this.coordination = coordination;
    }
}
