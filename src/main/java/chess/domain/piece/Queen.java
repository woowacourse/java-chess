package chess.domain.piece;

import chess.domain.position.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, RoleType.QUEEN);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        return isStraight(sourcePosition, targetPosition) || isDiagonal(sourcePosition, targetPosition);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
