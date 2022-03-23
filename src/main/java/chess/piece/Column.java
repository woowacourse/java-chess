package chess.piece;

import java.util.Arrays;

public enum Column {

    A, B, C, D, E, F, G, H;

    public static Column of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다."));
    }
}
