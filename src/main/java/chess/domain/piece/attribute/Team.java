package chess.domain.piece.attribute;

public enum Team {
    BLACK,
    WHITE,
    NONE;

    public Team changeTeam() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
