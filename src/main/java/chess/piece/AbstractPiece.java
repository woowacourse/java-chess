package chess.piece;

public abstract class AbstractPiece implements Piece {

    private final Name name;
    private final Color color;

    AbstractPiece(final Name name, final Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
