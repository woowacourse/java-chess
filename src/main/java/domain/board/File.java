package domain.board;

public enum File {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    ;

    private static final String INVALID_FILE = "열은 a ~ h까지만 가능합니다.";

    private final String name;

    File(final String name) {
        this.name = name;
    }

    public static File from(final String file) {
        try {
            return File.valueOf(file.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_FILE);
        }
    }

    public String getName() {
        return name;
    }
}
