package chess.domain.board;

import java.util.Arrays;

public enum File {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    ;

    private final String command;
    private final int position;

    File(final String command, final int position) {
        this.command = command;
        this.position = position;
    }

    public static File from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일은 1 ~ 8 사이의 값이어야 합니다."));
    }

    public int calculateGap(final File target) {
        return this.position - target.position;
    }
}
