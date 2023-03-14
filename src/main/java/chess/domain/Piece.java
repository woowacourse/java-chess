package chess.domain;

public abstract class Piece {

    private final Type type;
    private final Side side;

    public Piece(final Type type, final Side side) {
        validate(type, side);
        this.type = type;
        this.side = side;
    }

    protected abstract void validate(final Type type, final Side side);
}
