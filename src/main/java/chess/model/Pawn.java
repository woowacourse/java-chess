package chess.model;

public class Pawn extends Piece {
    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;

    public Pawn(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public boolean canMove(ChessPosition target) {
        Distance distance = target.calculateDistance(this.chessPosition);
        if (chessPosition.isPawnInitialPosition(side)) {
            return canMoveForwardWith(distance, DISPLACEMENT) ||
                    canMoveForwardWith(distance, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(distance, DISPLACEMENT);
    }

    private boolean canMoveForwardWith(Distance distance, int displacement) {
        return distance.isForward() && distance.hasSame(displacement);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "p";
        }
        return "P";
    }
}
