package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private final Xpoint xpoint;
    private final Ypoint ypoint;
    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (Xpoint xpoint : Xpoint.values()) {
            for (Ypoint ypoint : Ypoint.values()) {
                CACHE.put(xpoint.getName() + ypoint.getValue(), new Position(xpoint, ypoint));
            }
        }
    }

    public Position(Xpoint xpoint, Ypoint ypoint) {
        this.xpoint = xpoint;
        this.ypoint = ypoint;
    }

    public static Position valueOf(String value) {
        Position position = CACHE.get(value);
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException("잘못된 좌표입니다.");
        }
        return position;
    }
}
