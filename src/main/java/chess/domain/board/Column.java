package chess.domain.board;

public enum Column {

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H");

    private String name;

    Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
