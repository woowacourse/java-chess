package chessgame.domain.piece;

public enum Camp {

    WHITE,
    BLACK,
    EMPTY;

    public Camp change() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
