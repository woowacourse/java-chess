package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    ;

    private final String command;

    Rank(final String command) {
        this.command = command;
    }

    public static Rank from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("랭크는 A ~ H 사이의 값이어야 합니다."));
    }
}
