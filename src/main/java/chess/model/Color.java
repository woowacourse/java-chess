package chess.model;

import java.util.List;

public enum Color {

    WHITE, BLACK, NOTHING;

    public static List<Color> getPlayerColors() {
        return List.of(WHITE, BLACK);
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public boolean isEnemy(Color other) {
        if (this.equals(NOTHING) || other.equals(NOTHING) || this.equals(other)) {
            return false;
        }
        return true;
    }

    public boolean isAlly(Color other) {
        return !this.isEmpty() && this.equals(other);
    }

    public boolean isEmpty() {
        return this.equals(Color.NOTHING);
    }
}
