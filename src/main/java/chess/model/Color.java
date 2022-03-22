package chess.model;

public enum Color {

    WHITE, BLACK, NOTHING;

    public boolean isBlack() {
        return this.equals(BLACK);
    }
}
