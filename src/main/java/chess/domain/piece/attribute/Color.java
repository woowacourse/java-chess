package chess.domain.piece.attribute;

public enum Color {
    WHITE,
    BLACK
    ;

    public boolean isBlack() {
        return this == BLACK;
    }
}
