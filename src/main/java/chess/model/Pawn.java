package chess.model;

public class Pawn extends Piece {
    public Pawn(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public boolean canMove(ChessPosition target) {
        Distance distance = target.calculateDistance(this.chessPosition);
        if (chessPosition.isPawnInitialPosition(side)) {
            return canMoveForwardWith(distance, 1) ||
                    canMoveForwardWith(distance, 2);
        }
        return canMoveForwardWith(distance, 1);
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
