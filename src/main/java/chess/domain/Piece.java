package chess.domain;

public abstract class Piece {
    private Type type;
    private Side side;

    public Piece(final Type type, final Side side) {
        this.type = type;
        this.side = side;
    }

    public abstract void move(Position end);
}
