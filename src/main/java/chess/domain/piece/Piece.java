package chess.domain.piece;

public abstract class Piece {

    private final String name;

    protected Piece(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
