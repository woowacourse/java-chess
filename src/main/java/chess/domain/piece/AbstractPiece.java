package chess.domain.piece;

import chess.domain.game.Board;

import java.util.Objects;

public abstract class AbstractPiece implements Piece {
    
    protected final Color color;
    protected final DirectionGroup directionGroup;
    
    public AbstractPiece(Color color, DirectionGroup directionGroup) {
        this.color = color;
        this.directionGroup = directionGroup;
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
        return color == that.color;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
    
    @Override
    public boolean isSameColorAs(Color color) {
        return this.color == color;
    }
    
    protected boolean isObstacleAtDirection(Position sourcePosition, Position targetPosition, Direction direction,
                                            Board board) {
        while (sourcePosition.isInRange()) {
            sourcePosition = direction.addDegreeTo(sourcePosition);
            if (sourcePosition.equals(targetPosition)) {
                return false;
            }

            if (!board.isBlank(sourcePosition)) {
                return true;
            }
        }
        return false;
    }
}
