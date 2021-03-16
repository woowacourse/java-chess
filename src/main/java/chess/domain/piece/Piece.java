package chess.domain.piece;

public class Piece {

    private final Color color;
    private final Shape shape;

    public Piece(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isKing() {
        return this.shape == Shape.KING;
    }

    public String getNotation() {
        return shape.getNotation(color);
    }
}
