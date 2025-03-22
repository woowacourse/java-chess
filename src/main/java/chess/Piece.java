package chess;

public class Piece {

    private final Color color;
    private final PieceType pieceType;

    public Piece(
        final Color color,
        final PieceType pieceType
    ) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
