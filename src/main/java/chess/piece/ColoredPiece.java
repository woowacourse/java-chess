package chess.piece;

public class ColoredPiece {

    private final Piece piece;
    private final Color color;

    public ColoredPiece(Piece piece, Color color) {
        this.piece = piece;
        this.color = color;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isSamePieceAs(Piece piece) {
        return this.piece == piece;
    }

    public boolean hasColorOf(Color color) {
        return this.color == color;
    }
}
