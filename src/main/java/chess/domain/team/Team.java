package chess.domain.team;

public enum Team {

    BLACK,
    WHITE,
    NONE;

    public Team reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
