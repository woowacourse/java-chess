package chess.domain.piece;

public abstract class Piece {

    private final Name name;
    private final Color color;

    public Piece(Name name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isEmpty() {
        return false;
    }
}
