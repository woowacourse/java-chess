package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NONE,
    ;

    public boolean isWhite() {
        return this == WHITE;
    }
}
