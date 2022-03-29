package chess.model;

public enum PieceColor {
    BLACK, EMPTY, WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }
}
