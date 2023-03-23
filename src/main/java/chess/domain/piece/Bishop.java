package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, RoleType.BISHOP);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        return isDiagonal(sourcePosition, targetPosition);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
