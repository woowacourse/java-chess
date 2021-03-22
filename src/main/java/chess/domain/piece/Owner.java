package chess.domain.piece;

public enum Owner {
    BLACK,
    WHITE,
    NONE;

    public Owner reverse() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        if (this.equals(WHITE)) {
            return BLACK;
        }

        throw new IllegalStateException();
    }

    public boolean isEnemy(final Owner other) {
        return this.reverse().equals(other);
    }

    public boolean isSameTeam(final Owner other) {
        return this.equals(other);
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public boolean isWhite() {
        return this.equals(WHITE);
    }
}
