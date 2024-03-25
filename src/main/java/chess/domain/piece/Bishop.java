package chess.domain.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        return sourcePosition.isOnSameDiagonalAs(targetPosition);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean exists() {
        return true;
    }
}
