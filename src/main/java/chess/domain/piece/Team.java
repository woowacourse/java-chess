package chess.domain.piece;

public enum Team {

    WHITE,
    BLACK;

    public Team getEnemy() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
