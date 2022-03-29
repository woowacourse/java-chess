package chess.model;

public enum Team {

    BLACK,
    WHITE,
    NONE,
    ;

    public Team opponent() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
