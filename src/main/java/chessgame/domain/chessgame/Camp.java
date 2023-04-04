package chessgame.domain.chessgame;

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
