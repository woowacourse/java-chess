package chess.model;

public enum Color {

    WHITE, BLACK, NOTHING;

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public boolean isEnemy(Color other) {
        if (this.equals(NOTHING) || other.equals(NOTHING)) {
            return false;
        }
        if (this.equals(other)) {
            return false;
        }
        return true;
    }
}
