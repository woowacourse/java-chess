package chess.domain.piece;

import java.util.List;
import java.util.Objects;

public abstract class AbstractPiece implements Piece {
    
    protected final Color color;
    protected final Position position;
    
    public AbstractPiece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }
    
//    protected boolean isMovable(Position position, List<Direction> directions, int ableLength) {
//        return directions.stream()
//                         .anyMatch(direction -> this.position.canMove(position, direction, ableLength));
//    }
    
    protected Direction findDirection(Position position, List<Direction> directions, int ableLength) {
        return directions.stream()
                         .filter(direction -> this.position.canMove(position, direction, ableLength))
                         .findAny().orElseThrow(() -> new IllegalArgumentException("기물이 이동할 수 없는 위치입니다."));
    }
    
    protected String changeColorSymbol(String symbol) {
        if (color.isBlack()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPiece that = (AbstractPiece) o;
        return color == that.color && Objects.equals(position, that.position);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
    
    @Override
    public boolean isSameColor(Color color) {
        return color == this.color;
    }
    
    protected boolean isObstacleAtDirection(Position position, Direction direction, List<List<Piece>> board) {
        int dx = this.position.getX().getPoint();
        int dy = this.position.getY().getPoint();
        for (int i = 1; i < 7; i++) {
            dx += direction.getYDegree();
            dy += direction.getXDegree();
            if (dy < 0 || dy > 7 || dx < 0 || dx > 7) {
                break;
            }
            if (dy == position.getY().getPoint() && dx == position.getX().getPoint()) {
                break;
            }
            if (!board.get(dx).get(dy).isSameColor(Color.BLANK)){
                return true;
            }
        }
        return false;
    }
}
