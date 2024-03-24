package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE,
    ;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
