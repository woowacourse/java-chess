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
        if (Objects.isNull(position)) {
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

    public Position doubleUp() {
        return up().up();
    }

    public Position down() {
        return valueOf(xpoint, ypoint.down());
    }

    public Position doubleDown() {
        return down().down();
    }

    public Position left() {
        return valueOf(xpoint.left(), ypoint);
    }

    public Position right() {
        return valueOf(xpoint.right(), ypoint);
    }

    public Position leftUp() {
        Position movedPosition = valueOf(xpoint.left(), ypoint.up());
        if (isSamePosition(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position rightUp() {
        Position movedPosition = valueOf(xpoint.right(), ypoint.up());
        if (isSamePosition(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position leftDown() {
        Position movedPosition = valueOf(xpoint.left(), ypoint.down());
        if (isSamePosition(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position rightDown() {
        Position movedPosition = valueOf(xpoint.right(), ypoint.down());
        if (isSamePosition(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    private boolean isSamePosition(Position movedPosition) {
        return movedPosition.isSameX(this.xpoint) || movedPosition.isSameY(this.ypoint);
    }

    public boolean isSameX(Xpoint xpoint) {
        return this.xpoint.equals(xpoint);
    }

    public boolean isSameY(Ypoint ypoint) {
        return this.ypoint.equals(ypoint);
    }

    public List<Position> upVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.up()) {
            position = position.up();
            vector.add(position);
        }
        return vector;
    }

    public List<Position> downVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.down()) {
            position = position.down();
            vector.add(position);
        }
        return vector;
    }

    public List<Position> leftVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.left()) {
            position = position.left();
            vector.add(position);
        }
        return vector;
    }

    public List<Position> rightVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.right()) {
            position = position.right();
            vector.add(position);
        }
        return vector;
    }
}
