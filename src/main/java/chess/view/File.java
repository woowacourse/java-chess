package chess.view;

import java.util.Arrays;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private static final String WRONG_FILE_ERROR_MESSAGE = "잘못된 file 입니다.";

    private final String userIndex;
    private final int coordinateIndex;

    File(final String userIndex, final int coordinateIndex) {
        this.userIndex = userIndex;
        this.coordinateIndex = coordinateIndex;
    }

    public static int findByUserIndex(String searchIndex) {
        File foundFile = Arrays.stream(values())
                .filter(file -> file.userIndex.equalsIgnoreCase(searchIndex))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_FILE_ERROR_MESSAGE));
        return foundFile.coordinateIndex;
    }

}
