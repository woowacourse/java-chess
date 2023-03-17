package chess.domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    NEUTRAL,
    ;

    public static Camp convert(Camp turnCamp) {
        if (turnCamp == BLACK) {
            return WHITE;
        }

        return BLACK;
    }
}
