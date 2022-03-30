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

    public boolean isWhite() {
        return this.equals(WHITE);
    }

    public boolean isEnemy(Color other) {
        if (this.equals(other) || this.equals(NOTHING) || other.equals(NOTHING)) {
            return false;
        }
        return true;
    }

    public Color changeToOpposite() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return NOTHING;
    }
}
