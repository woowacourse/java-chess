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

    public boolean isEnemy(Owner other){
        return this.reverse().equals(other);
    }

    public boolean isSameTeam(Owner other){
        return this.equals(other);
    }
}
