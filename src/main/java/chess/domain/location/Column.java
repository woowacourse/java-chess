package chess.domain.location;

import java.util.Arrays;

public enum Column {
    A, B, C, D, E, F, G, H;

    public static Column of(String input) {
        return Arrays.stream(values())
                .filter(column -> column.isName(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Column 입력입니다."));
    }

    private boolean isName(String name) {
        return this.name().equalsIgnoreCase(name);
    }
}
