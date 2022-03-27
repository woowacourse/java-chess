package chess.domain.piece.attribute;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public Team oppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
