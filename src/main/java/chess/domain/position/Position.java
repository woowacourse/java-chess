package chess.domain.position;

import java.util.Objects;

public class Position {
    
    private static final int RANK_CHARACTER_INDEX = 0;
    private static final int FILE_CHARACTER_INDEX = 1;
    
    private static final int POSITION_WORD_LENGTH = 2;
    
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
        if (value.length() != POSITION_WORD_LENGTH) {
            throw new IllegalArgumentException("위치는 a1 과 같은 형식의 2글자이어야 합니다.");
        }
        
        int x = value.charAt(RANK_CHARACTER_INDEX) - 'a';
        int y = value.charAt(FILE_CHARACTER_INDEX) - '1';
        return Position.of(x, y);
    }
    
    public Position add(int xDistance, int yDistance) {
        return new Position(this.x.add(xDistance), this.y.add(yDistance));
    }
    
    public boolean isInRange() {
        return x.isInRange() && y.isInRange();
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
    
    public boolean existsAtRankOf(int point) {
        return y.equalsTo(point);
    }
}
