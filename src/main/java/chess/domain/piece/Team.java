package chess.domain.piece;

public enum Team {

    WHITE,
    BLACK;

    public Team findOpposite() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
