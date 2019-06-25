package model.game;

public enum Player {
    WHITE,
    BLACK;

    public Player toggle() {
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