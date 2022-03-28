package chess.model;

import java.util.List;

public enum Color {

    BLACK, WHITE, NOTHING;

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public boolean isEnemy(Color other) {
        if (this.equals(NOTHING) || other.equals(NOTHING)) {
            return false;
        }
        return !this.equals(other);
    }

    public boolean isAlly(Color other) {
        return this.equals(other);
    }

    public boolean isEmpty() {
        return this.equals(Color.NOTHING);
    }

    public static List<Color> getPlayerColors() {
        return List.of(BLACK, WHITE);
    }
}
