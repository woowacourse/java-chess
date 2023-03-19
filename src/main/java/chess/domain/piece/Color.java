package chess.domain.piece;

public enum Color {
    BLACK(1),
    WHITE(-1),
    EMPTY(0),
    ;
    private final int direction;

    Color(int direction) {
        this.direction = direction;
    }

    public boolean isOpposite(Color color) {
        return this.getReverseColor() == color;
    }

    public Color getReverseColor() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }

    public int getDirection() {
        return direction;
    }
}
