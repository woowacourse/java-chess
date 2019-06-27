package model.game;

public enum Color {
    WHITE,
    BLACK;

    public Color toggle() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public static Color turnCountToTeamColor(int turnCount) {
        if (turnCount < 1) {
            throw new IllegalArgumentException();
        }
        return values()[(turnCount - 1) % values().length];
    }

    @Override
    public String toString() {
        return this == WHITE ? "백" : "흑";
     }
}