package chessgame.domain.square;

public enum Camp {

    WHITE,
    BLACK;

    public Camp change() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
