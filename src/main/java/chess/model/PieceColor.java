package chess.model;

public enum PieceColor {
    BLACK, WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }
}
