package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(1, "1"),
    B(2, "2"),
    C(3, "3"),
    D(4, "4"),
    E(5, "5"),
    F(6, "6"),
    G(7, "7"),
    H(8, "8");

    private static final String WRONG_FILE_ERROR_MESSAGE = "잘못된 file 입니다.";

    private final int address;
    private final String index;

    File(final int address, final String index) {
        this.address = address;
        this.index = index;
    }

    public static int findByIndex(String index) {
        File foundFile = Arrays.stream(values())
                .filter(file -> file.index.equalsIgnoreCase(index))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_FILE_ERROR_MESSAGE));
        return foundFile.address;
    }

}
