package chess.board;

public record BoardVector(
        int dx,
        int dy
) {

    public int getAbsDx() {
        return Math.abs(dx);
    }

    public int getAbsDy() {
        return Math.abs(dy);
    }

    public static BoardVector createVector(Position start, Position end) {
        int dx = end.column().ordinal() - start.column().ordinal();
        int dy = end.row().getValue() - start.row().getValue();
        return new BoardVector(dx, dy);
    }

    public boolean isOneQuadrant() {
        return dx > 0 && dy > 0;
    }

    public boolean isTwoQuadrant() {
        return dx < 0 && dy > 0;
    }

    public boolean isThreeQuadrant() {
        return dx < 0 && dy < 0;
    }

    public boolean isFourQuadrant() {
        return dx > 0 && dy < 0;
    }

    public boolean isDxZero() {
        return dx == 0;
    }
}
