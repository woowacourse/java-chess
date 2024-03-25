package chess.model.piece;

import chess.model.Position;

public class None extends Piece {

    protected None(PieceType pieceType) {
        super(pieceType, Color.NONE);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }

    @Override
    public boolean canAttack(Position source, Position target) {
        return canMove(source, target);
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
