package chess.domain.piece;

public enum Team {

    BLACK,
    WHITE;

    public Team next() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }
}
