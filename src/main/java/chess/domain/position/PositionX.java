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
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다."));
    }

    public static PositionX of(int coordination) {
        return Arrays.stream(values())
                .filter(positionX -> positionX.coordination == coordination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌표입니다."));
    }

    public int displacementTo(PositionX positionX) {
        return positionX.coordination - coordination;
    }

    public PositionX displacedOf(int displacement) {
        return PositionX.of(coordination + displacement);
    }

    public String getName() {
        return name;
    }
}
