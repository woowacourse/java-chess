package chess.domain.piece.position;

import java.util.Arrays;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h"),
    OUT(-1, "Out");

    public static final int MIN_FILE_VALUE  = 1;
    public static final int MAX_FILE_VALUE  = 8;

    private final int value;
    private final String name;

    File(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private static File of(int value) {
        return Arrays.stream(File.values())
            .filter(file -> file.value == value)
            .findFirst()
            .get();
    }

    public static File of(String name) {
        return Arrays.stream(File.values())
            .filter(file -> file.name().equals(name.toUpperCase()))
            .findFirst()
            .get();
    }

    public File getNext(int next) {
        if (value + next < MIN_FILE_VALUE || value + next > MAX_FILE_VALUE) {
            return OUT;
        }

        return File.of(value + next);
    }

    public String getName() {
        return name;
    }
}
