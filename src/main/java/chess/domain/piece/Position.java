package chess.domain.piece;

import java.util.Objects;
import java.util.stream.IntStream;

import static chess.ChessConstant.DEFAULT_INDEX_OF_BLACK_PAWN;
import static chess.ChessConstant.DEFAULT_INDEX_OF_WHITE_PAWN;

public class Position {
    
    private static final int MIN_MOVE_LENGTH = 1;
    
    private static final int RANK_CHARACTER_INDEX = 0;
    private static final int FILE_CHARACTER_INDEX = 1;
    
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
        int x = value.charAt(RANK_CHARACTER_INDEX) - 'a';
        int y = value.charAt(FILE_CHARACTER_INDEX) - '1';
        return Position.of(x, y);
    }
    
    public Position add(int xDistance, int yDistance) {
        return new Position(this.x.add(xDistance), this.y.add(yDistance));
    }
    
    public boolean canMove(Position targetPosition, Direction direction, int maxMoveLength) {
        return IntStream.rangeClosed(MIN_MOVE_LENGTH, maxMoveLength)
                        .mapToObj(distance -> {
                            Point xPoint = x.add(direction.getXDegree() * distance);
                            Point yPoint = y.add(direction.getYDegree() * distance);
                            return new Position(xPoint, yPoint);
                        })
                        .anyMatch(targetPosition::equals);
    }
    
    public boolean isInRange() {
        return !x.isOutOfBounds() && !y.isOutOfBounds();
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
    
    public boolean isAtDefaultPawnPosition(Color color) {
        if (color.isWhite()) {
            return y.equalsTo(DEFAULT_INDEX_OF_WHITE_PAWN);
        }
        
        return y.equalsTo(DEFAULT_INDEX_OF_BLACK_PAWN);
    }
}
