package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, RoleType.ROOK);
    }

    @Override
    protected boolean validMove(Position sourcePosition, Position targetPosition, Color targetColor) {
        return isStraight(sourcePosition, targetPosition);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
