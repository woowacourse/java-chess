package chess.model;

public class Queen extends Piece {
    public Queen(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public boolean canMove(ChessPosition target) {
        Distance distance = target.calculateDistance(this.chessPosition);
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
