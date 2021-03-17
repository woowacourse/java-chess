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
    
    protected boolean isMovable(Position position, List<Direction> directions, int ableLength) {
        return directions.stream()
                         .anyMatch(direction -> this.position.canMove(position, direction, ableLength));
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
}
