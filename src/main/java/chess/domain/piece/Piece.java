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
        if(pieceColor.equals(PieceColor.BLACK)){
            return pieceType.toBlack();
        }
        return pieceType.getType();
    }

    public boolean isColor(PieceColor color) {
        return pieceColor.equals(color);
    }
}
