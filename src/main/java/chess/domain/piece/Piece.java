package chess.domain.piece;

public class Piece {

    private final PieceType pieceType;
    private final ColorType colorType;

    public Piece(PieceType pieceType, ColorType colorType) {
        this.pieceType = pieceType;
        this.colorType = colorType;
    }

    public boolean isBlack() {
        return colorType.equals(ColorType.BLACK);
    }

    public boolean isSameType(String pieceViewName) {
        return pieceType.name().equals(pieceViewName);
    }
}
