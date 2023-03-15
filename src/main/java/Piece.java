public abstract class Piece {
    protected final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    public abstract boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition);

//    public
}
