package chess.model;

public class Bishop extends Piece {

    public Bishop(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        return distance.isDiagonalMovement();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "b";
        }
        return "B";
    }
}
