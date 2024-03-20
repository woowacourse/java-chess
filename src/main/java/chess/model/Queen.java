package chess.model;

public class Queen extends Piece {

    public Queen(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        return distance.isCrossMovement() || distance.isDiagonalMovement();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "q";
        }
        return "Q";
    }
}
