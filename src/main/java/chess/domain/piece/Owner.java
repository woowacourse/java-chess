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
        throw new IllegalStateException("유효하지 않은 색깔입니다.");
    }

    public boolean isEnemy(final Owner other) {
        if (this.equals(BLACK)) {
            return WHITE.equals(other);
        }
        if (this.equals(WHITE)) {
            return BLACK.equals(other);
        }
        if (this.equals(NONE)) {
            throw new IllegalArgumentException("적이 존재하지 않는 색입니다.");
        }
        return false;
    }

    public boolean isSame(final Owner other) {
        return this.equals(other);
    }

    public boolean isDifferent(final Owner other) {
        return !this.equals(other);
    }
}
