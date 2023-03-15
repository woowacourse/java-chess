package domain.piece;

import domain.piecetype.PieceType;

public class ConcretePiece implements Piece {

    private final PieceType pieceType;
    private final Camp camp;

    public ConcretePiece(PieceType pieceType, Camp camp) {
        this.pieceType = pieceType;
        this.camp = camp;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Camp getCamp() {
        return camp;
    }
}
