package chess.domain;

public class Piece {

    private final boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean hasSameColor(Piece otherPiece) {
        return isWhite == otherPiece.isWhite;
    }
}
