package chess.domain;

public class Piece {

    private Position position;
    private Shape shape;

    private Piece(final Position position, final Shape shape) {
        this.position = position;
        this.shape = shape;
    }
    public static Piece from(final int rank, final char file, final Shape shape) {
         return new Piece(Position.from(rank, file), shape);
    }

    public boolean isSameShape(Shape shape) {
        return this.shape == shape;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "position=" + position +
                ", shape=" + shape +
                '}';
    }

    public Piece getNewPiece(int file) {
        return new Piece(position.changePosition(file), this.shape);
    }

    public int getRank() {
        return position.getRank();
    }

    public char getFile() {
        return position.getFile();
    }

    public char getName(String color) {
        return this.shape.findNameByColor(color);
    }
}
