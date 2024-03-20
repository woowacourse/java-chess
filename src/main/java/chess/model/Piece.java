package chess.model;

public abstract class Piece implements MoveStrategy {
    protected final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    public abstract String getText();
}
