package chess.domain.piece;

public abstract class Piece {

    protected PieceType pieceType;
    protected PieceColor pieceColor;

    protected Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    abstract void move();

    public String getName() {
        return pieceType.getType();
    }
}
