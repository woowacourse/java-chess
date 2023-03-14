package chess.domain;

public class Piece {

    private Position position;
    private Shape shape;

    private Piece(final Position position, final Shape shape) {
        this.position = position;
        this.shape = shape;
    }
    public static Piece of(final char rank, final int file, final Shape shape) {
         return new Piece(Position.from(rank, file), shape);
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

}
