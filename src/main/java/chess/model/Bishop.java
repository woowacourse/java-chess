package chess.model;

public class Bishop extends Piece {
    public Bishop(Side side, ChessPosition chessPosition) {
        super(side, chessPosition);
    }

    @Override
    public boolean canMove(ChessPosition target) {
        Distance distance = target.calculateDistance(this.chessPosition);
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
