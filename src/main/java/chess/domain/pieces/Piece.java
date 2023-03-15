package chess.domain.pieces;

public abstract class Piece {

    private final Name name;

    public Piece(final Name name) {
        this.name = name;
    }

    public abstract void canMove(final String start, final String end);
}
