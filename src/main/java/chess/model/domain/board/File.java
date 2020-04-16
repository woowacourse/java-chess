package chess.model.domain.board;

import java.util.Arrays;

public enum File {
    FIRST("a", 1),
    SECOND("b", 2),
    THIRD("c", 3),
    FOURTH("d", 4),
    FIFTH("e", 5),
    SIXTH("f", 6),
    SEVENTH("g", 7),
    EIGHTH("h", 8);

    private static final String NO_FILE_EXCEPTION_MESSAGE = "다음 File이 존재하지 않습니다.";

    private final String name;
    private final int number;

    File(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public boolean hasNextIncrement(int increment) {
        return Arrays.stream(File.values())
            .anyMatch(file -> file.number == this.number + increment);
    }

    public File findIncrement(int increment) throws IllegalArgumentException {
        return Arrays.stream(File.values())
            .filter(file -> file.number == this.number + increment)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NO_FILE_EXCEPTION_MESSAGE));
    }

}
