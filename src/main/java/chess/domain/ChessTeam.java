package chess.domain;

public enum ChessTeam {
    BLACK(-1),
    EMPTY(0),
    WHITE(1);

    private int color;

    private ChessTeam(int color) {
        this.color = color;
    }

    public ChessTeam change() {
        if (color == 1) {
            return BLACK;
        }
        return WHITE;
    }

    public int color() {
        return this.color;
    }
}
