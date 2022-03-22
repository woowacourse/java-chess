package chess.domain;

import java.util.Arrays;

public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public static Column of(String value) {
        return Arrays.stream(values())
                .filter(column -> column.name()
                        .equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 열 이름이 들어왔습니다."));
    }
}
