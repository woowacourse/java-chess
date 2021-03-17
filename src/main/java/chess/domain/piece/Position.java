package chess.domain.piece;

import java.util.Objects;
import java.util.stream.IntStream;

public class Position {
    
    private static final int DEFAULT_MOVE_LENGTH = 1;
    
    // TODO 캐싱 구현하기
    private final Point x;
    private final Point y;
    
    private Position(Point x, Point y) {
        this.x = x;
        this.y = y;
    }
    
    public static Position of(int x, int y) {
        final Point pointX = Point.from(x);
        final Point pointY = Point.from(y);
        return new Position(pointX, pointY);
    }
    
    public static Position of(String value) {
        int x = value.charAt(1) - '1';
        int y = value.charAt(0) - 'a';
        return Position.of(x, y);
    }
    
    public boolean canMove(Position position, Direction direction, int ableLength) {
        return IntStream.rangeClosed(DEFAULT_MOVE_LENGTH, ableLength)
                        .mapToObj(distance -> {
                            Point dx = x.add(direction.getYDegree() * distance);
                            Point dy = y.add(direction.getXDegree() * distance);
                            return new Position(dx, dy);
                        })
                        .anyMatch(position1 -> position1.equals(position));
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
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    public Point getX() {
        return x;
    }
    
    public Point getY() {
        return y;
    }
}
