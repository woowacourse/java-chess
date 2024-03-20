package chess.model;

public class Knight extends Piece {
    private static final int DISPLACEMENT = 3;

    public Knight(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public boolean canMove(ChessPosition target) {
        Distance distance = target.calculateDistance(this.chessPosition);
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
