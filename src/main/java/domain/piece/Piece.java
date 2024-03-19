package domain.piece;

public abstract class Piece {
    private final String name;
    private final PieceColor color;

    public Piece(final String name, final PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public boolean isColor(final PieceColor color) {
        return this.color == color;
    }
}
