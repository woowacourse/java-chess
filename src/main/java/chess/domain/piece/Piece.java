package chess.domain.piece;

public abstract class Piece {

    protected PieceType pieceType;

    protected Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    abstract void move();

    public String getName() {
        return pieceType.getType();
    }
}
