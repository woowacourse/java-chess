package chess.domain;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private String column;

    Column(String column){
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
