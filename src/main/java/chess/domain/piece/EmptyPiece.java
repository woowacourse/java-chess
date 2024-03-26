package chess.domain.piece;

import chess.domain.position.Position;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.EMPTY, PieceType.EMPTY);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return false;
    }

    @Override
    public boolean canCatch(Position from, Position to) {
        return false;
    }
}
