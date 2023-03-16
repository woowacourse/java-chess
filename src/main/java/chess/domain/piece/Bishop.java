package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition, Color color) {
        return isDiagonal(sourcePosition, targetPosition) && isNotMyPosition(sourcePosition, targetPosition)
                && isNotSameColor(color);
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Piece move() {
        return new Bishop(getColor());
    }
}
