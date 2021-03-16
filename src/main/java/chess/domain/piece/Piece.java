package chess.domain.piece;

public class Piece {

    private final Color color;
    private final Shape shape;
    private final Position position;

    public Piece(Color color, Shape shape, Position position) {
        this.color = color;
        this.shape = shape;
        this.position = position;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;

        final Piece piece = (Piece) o;

        if (color != piece.color) return false;
        return shape == piece.shape;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (shape != null ? shape.hashCode() : 0);
        return result;
    }

}
