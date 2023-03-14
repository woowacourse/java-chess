public enum Column {
    A(0, "a"),
    B(1, "b"),
    C(2, "c"),
    D(3, "d"),
    E(4, "e"),
    F(5, "f"),
    G(6,"g"),
    H(7,"h");

    private final int index;
    private final String value;

    Column(int index, String value) {
        this.index = index;
        this.value = value;
    }
}
