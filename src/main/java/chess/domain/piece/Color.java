package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE,
    NONE;

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public boolean isWhite() {
        return this.equals(WHITE);
    }
}
