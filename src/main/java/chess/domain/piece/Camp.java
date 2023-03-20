package chess.domain.piece;

public enum Camp {
    BLACK,
    WHITE,
    ;

    public static Camp convert(Camp turnCamp) {
        if (turnCamp == BLACK) {
            return WHITE;
        }

        return BLACK;
    }
}
