package domain.position;

public enum File {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H");

    private final String name;

    File(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
