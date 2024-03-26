package domain;

public enum Team {
    WHITE, BLACK, NONE;

    public Team otherTeam() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return NONE;
    }

    public boolean isPresent() {
        return !this.equals(NONE);
    }

    public boolean isEmpty() {
        return this.equals(NONE);
    }
}
