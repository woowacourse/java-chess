package chess.domain.board.position;

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

    public static Position valueOf(Xpoint xpoint, Ypoint ypoint) {
        return valueOf(xpoint.getName() + ypoint.getValue());
    }

    public static List<Position> generate() {
        return new ArrayList<>(CACHE.values());
    }

    public Position up() {
        return valueOf(xpoint, ypoint.up());
    }

    public Position down() {
        return valueOf(xpoint, ypoint.down());
    }

    public Position left() {
        return valueOf(xpoint.left(), ypoint);
    }

    public Position right() {
        return valueOf(xpoint.right(), ypoint);
    }

    public Position leftUp() {
        return valueOf(xpoint.left(), ypoint.up());
    }

    public Position rightUp() {
        return valueOf(xpoint.right(), ypoint.up());
    }

    public Position downLeft() {
        return valueOf(xpoint.left(), ypoint.down());
    }

    public Position downRight() {
        return valueOf(xpoint.right(), ypoint.down());
    }
}
