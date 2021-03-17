package chess.domain.position;

import java.util.*;

public class Position {
    private final Xpoint xpoint;
    private final Ypoint ypoint;
    private static final List<Map<String, Position>> CACHE = new ArrayList<>();

    static {
        for (Ypoint ypoint : Ypoint.values()) {
            Map<String, Position> positions = new LinkedHashMap<>();
            for (Xpoint xpoint : Xpoint.values()) {
                positions.put(xpoint.getName() + ypoint.getValue(), new Position(xpoint, ypoint));
            }
            CACHE.add(positions);
        }
    }

    public Position(Xpoint xpoint, Ypoint ypoint) {
        this.xpoint = xpoint;
        this.ypoint = ypoint;
    }

    public static Position valueOf(String value) {
        return CACHE.stream()
                .filter(map -> map.containsKey(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 좌표입니다."))
                .get(value);
    }

    public static List<Map<String, Position>> generate() {
        return new ArrayList<>(CACHE);
    }
}
