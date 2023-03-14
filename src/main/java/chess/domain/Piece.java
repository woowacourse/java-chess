package chess.domain;

public abstract class Piece {

    private final String name;
    private final Side side;

    public Piece(final String name, final Side side) {
        validate(name, side);
        this.name = name;
        this.side = side;
    }

    protected abstract void validate(final String name, final Side side);
}
