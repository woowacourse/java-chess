package domain.piece;

import domain.piecetype.PieceType;

public class EmptyPiece implements Piece {

    @Override
    public PieceType getPieceType() {
        return null;
    }

    @Override
    public Camp getCamp() {
        return null;
    }
}
