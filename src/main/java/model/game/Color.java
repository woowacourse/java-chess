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

    @Override
    public String toString() {
        return this == WHITE ? "백" : "흑";
     }
}