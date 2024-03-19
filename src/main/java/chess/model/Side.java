package chess.model;

public enum Side {
    BLACK,
    WHITE;

    public boolean isWhite() {
        return this.equals(WHITE);
    }
}
