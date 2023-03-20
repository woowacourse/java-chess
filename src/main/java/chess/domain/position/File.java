package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private static final String WRONG_FILE_ERROR_MESSAGE = "잘못된 file 입니다.";

    private final int address;
    private final String index;

    File(final int address, final String index) {
        this.address = address;
        this.index = index;
    }

    public static int findByIndex(final String index) {
        File foundFile = Arrays.stream(values())
            .filter(file -> file.index.equalsIgnoreCase(index))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_FILE_ERROR_MESSAGE));
        return foundFile.address;
    }

}
