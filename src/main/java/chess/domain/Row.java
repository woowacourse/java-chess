package chess.domain;

public enum Row {
    a("a", 0),
    b("b", 1),
    c("c", 2),
    d("d", 3),
    e("e", 4),
    f("f", 5),
    g("g", 6),
    h("h", 7);

    private String name;
    private int index;

    Row(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
