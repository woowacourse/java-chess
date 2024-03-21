package chess.model;

public enum Side {
    BLACK,
    WHITE;

    public boolean isWhite() {
        return this.equals(WHITE);
    }

    public boolean isUpperSide() {
        return this.equals(BLACK);
    }
}
