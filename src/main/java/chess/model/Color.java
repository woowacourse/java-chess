package chess.model;

import java.util.List;

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

    public boolean isAlly(Color other) {
        return this.equals(other);
    }

    public boolean isEmpty() {
        return this.equals(Color.NOTHING);
    }

    public static List<Color> getPlayerColors() {
        return List.of(WHITE, BLACK);
    }
}
