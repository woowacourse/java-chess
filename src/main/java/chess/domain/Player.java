package chess.domain;

public enum Player {
    WHITE,
    BLACK,
    EMPTY;

    public Player changePlayer() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
