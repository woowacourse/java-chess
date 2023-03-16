package chess.piece;

public enum Color {

    BLACK,
    WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
