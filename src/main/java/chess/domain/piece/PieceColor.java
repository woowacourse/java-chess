package chess.domain.piece;

public enum PieceColor {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }
}
