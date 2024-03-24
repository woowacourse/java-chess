package chess.model.piece;

import chess.model.Position;

public class None extends Piece {

    protected None(PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
