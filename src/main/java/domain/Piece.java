package domain;

public class Piece {

    private final PieceType pieceType;
    private final Camp camp;

    public Piece(PieceType pieceType, Camp camp) {
        this.pieceType = pieceType;
        this.camp = camp;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Camp getCamp() {
        return camp;
    }
}
