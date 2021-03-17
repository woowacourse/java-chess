package chess.domain.position;

import java.util.*;

public class Position {
    private final Xpoint xpoint;
    private final Ypoint ypoint;
    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (Ypoint ypoint : Ypoint.values()) {
            for (Xpoint xpoint : Xpoint.values()) {
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
        if(Objects.isNull(position)){
            throw new IllegalArgumentException("잘못된 좌표입니다.");
        }
        return position;
    }
    public static List<Position> generate() {
        return new ArrayList<>(CACHE.values());
    }
}
