package chess.model;

public class Piece {

    private final Name name;
    private final Color color;

    public Piece(final Name name, final Color color) {
        this.name = name;
        this.color = color;
    }

    public Name getName() {
        return name;
    }

    public boolean isBlack() {
        return color.isBlack();
    }
}
