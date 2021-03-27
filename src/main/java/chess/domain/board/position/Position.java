package chess.domain.board.position;

import java.util.*;

public class Position {
    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (Ypoint ypoint : Ypoint.values()) {
            for (Xpoint xpoint : Xpoint.values()) {
                CACHE.put(xpoint.getName() + ypoint.getValue(), new Position(xpoint, ypoint));
            }
        }
    }

    private final Xpoint xpoint;
    private final Ypoint ypoint;

    private Position(Xpoint xpoint, Ypoint ypoint) {
        this.xpoint = xpoint;
        this.ypoint = ypoint;
    }

    public static Position of(String value) {
        Position position = CACHE.get(value);
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException("잘못된 좌표입니다.");
        }
        return position;
    }

    public static Position of(Xpoint xpoint, Ypoint ypoint) {
        return of(xpoint.getName() + ypoint.getValue());
    }

    public static List<Position> generate() {
        return new ArrayList<>(CACHE.values());
    }

    public Position up() {
        return of(xpoint, ypoint.up());
    }

    public Position doubleUp() {
        return of(xpoint, ypoint.up(2));
    }

    public Position down() {
        return of(xpoint, ypoint.down());
    }

    public Position doubleDown() {
        return of(xpoint, ypoint.down(2));
    }

    public Position left() {
        return of(xpoint.left(), ypoint);
    }

    public Position right() {
        return of(xpoint.right(), ypoint);
    }

    public Position leftUp() {
        Position movedPosition = of(xpoint.left(), ypoint.up());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position rightUp() {
        Position movedPosition = of(xpoint.right(), ypoint.up());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position leftDown() {
        Position movedPosition = of(xpoint.left(), ypoint.down());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position rightDown() {
        Position movedPosition = of(xpoint.right(), ypoint.down());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position upUpLeft() {
        Position movedPosition = of(xpoint.left(), ypoint.up(2));
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position upUpRight() {
        Position movedPosition = of(xpoint.right(), ypoint.up(2));
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position downDownLeft() {
        Position movedPosition = of(xpoint.left(), ypoint.down(2));
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position downDownRight() {
        Position movedPosition = of(xpoint.right(), ypoint.down(2));
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position leftLeftUp() {
        Position movedPosition = of(xpoint.left(2), ypoint.up());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position leftLeftDown() {
        Position movedPosition = of(xpoint.left(2), ypoint.down());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position rightRightUp() {
        Position movedPosition = of(xpoint.right(2), ypoint.up());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    public Position rightRightDown() {
        Position movedPosition = of(xpoint.right(2), ypoint.down());
        if (isSameXOrY(movedPosition)) {
            return this;
        }
        return movedPosition;
    }

    private boolean isSameXOrY(Position movedPosition) {
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

    public List<Position> leftUpVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.leftUp()) {
            position = position.leftUp();
            vector.add(position);
        }
        return vector;
    }

    public List<Position> leftDownVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.leftDown()) {
            position = position.leftDown();
            vector.add(position);
        }
        return vector;
    }

    public List<Position> rightUpVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.rightUp()) {
            position = position.rightUp();
            vector.add(position);
        }
        return vector;
    }

    public List<Position> rightDownVector() {
        List<Position> vector = new ArrayList<>();
        Position position = this;
        while (position != position.rightDown()) {
            position = position.rightDown();
            vector.add(position);
        }
        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return xpoint == position.xpoint && ypoint == position.ypoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xpoint, ypoint);
    }

    @Override
    public String toString() {
        return xpoint.name() + ypoint.getValue();
    }

    public int yValue() {
        return ypoint.getValue();
    }
}
