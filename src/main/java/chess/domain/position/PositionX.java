package chess.domain.position;

import java.util.Arrays;

public enum PositionX {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String name;
    private final int coordination;

    PositionX(final String name, final int coordination) {
        this.name = name;
        this.coordination = coordination;
    }

    public static PositionX of(String name) {
        return Arrays.stream(values())
                .filter(positionX -> positionX.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 좌표입니다."));
    }

    public String getName() {
        return name;
    }

    public int getCoordination() {
        return coordination;
    }
}
