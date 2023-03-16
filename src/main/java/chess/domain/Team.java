package chess.domain;

public enum Team {
    BLACK,
    WHITE;

    public static Team change(Team team) {
        if (team == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
