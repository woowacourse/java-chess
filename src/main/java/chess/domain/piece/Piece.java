package chess.domain.piece;

public abstract class Piece {

    protected final PieceType pieceType;

    public Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public abstract boolean isEmpty();

    public abstract boolean isBlack();

    public abstract boolean isWhite();

    public boolean isType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public PieceType getType() {
        return pieceType;
    }
}
