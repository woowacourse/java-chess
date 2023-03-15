package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8),
    ;

    private final String command;
    private final int position;

    Rank(final String command, final int position) {
        this.command = command;
        this.position = position;
    }

    public static Rank from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("랭크는 A ~ H 사이의 값이어야 합니다."));
    }

    public int calculateGap(final Rank target) {
        return this.position - target.position;
    }
}
