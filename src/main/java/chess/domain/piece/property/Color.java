package chess.domain.piece.property;

public enum Color {

    BLACK,
    WHITE,
    NONE;

    public boolean isBlack() {
        return this.equals(BLACK);
    }
}
