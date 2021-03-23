package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;

import java.util.Objects;

public abstract class AbstractPiece implements Piece {
    
    private static final String ERROR_OBSTACLE_EXIST = "이동하는 경로 사이에 기물이 있습니다.";
    
    protected final Color color;
    protected final DirectionGroup directionGroup;
    
    public AbstractPiece(Color color, DirectionGroup directionGroup) {
        this.color = color;
        this.directionGroup = directionGroup;
    }
    
    @Override
    public boolean isSameColorAs(Color color) {
        return this.color == color;
    }
    
    @Override
    public boolean isBlank() {
        return false;
    }
    
    protected void checkObstacleExistsAtDirection(MovePosition movePosition, Direction direction, Board board) {
        Position sourcePosition = movePosition.getSourcePosition();
        sourcePosition = direction.addDegreeTo(sourcePosition);
        while (sourcePosition.isInRange() && !movePosition.isArrived(sourcePosition)) {
            checkPieceIsBlank(board, sourcePosition);
            sourcePosition = direction.addDegreeTo(sourcePosition);
        }
    }
    
    protected String changeColorSymbol(String symbol) {
        if (color.isBlack()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
    
    private void checkPieceIsBlank(Board board, Position sourcePosition) {
        if (!board.isBlank(sourcePosition)) {
            throw new IllegalArgumentException(ERROR_OBSTACLE_EXIST);
        }
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
}
