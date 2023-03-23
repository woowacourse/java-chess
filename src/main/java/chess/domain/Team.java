package chess.domain;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static Team change(final Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
