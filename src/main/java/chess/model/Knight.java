package chess.model;

public class Knight extends Piece {
    private static final int DISPLACEMENT = 3;

    public Knight(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(ChessPosition source, ChessPosition target) {
        Distance distance = target.calculateDistance(source);
        return distance.hasSame(DISPLACEMENT) && !distance.isCrossMovement() && !distance.isDiagonalMovement();
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "n";
        }
        return "N";
    }
}
