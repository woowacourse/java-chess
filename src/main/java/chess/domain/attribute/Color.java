package chess.domain.attribute;

public enum Color {
    WHITE,
    BLACK
    ;

    public boolean isBlack() {
        return this == BLACK;
    }
}
