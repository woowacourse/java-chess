package domain.piece;

public enum Team {
    BLACK,
    WHITE;

    public Team otherTeam() {
        if (BLACK.equals(this)) {
            return WHITE;
        }
        return BLACK;
    }
}
